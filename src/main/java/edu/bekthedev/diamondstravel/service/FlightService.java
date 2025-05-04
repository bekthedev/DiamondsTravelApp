package edu.bekthedev.diamondstravel.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bekthedev.diamondstravel.model.Flight;
import org.asynchttpclient.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.asynchttpclient.Dsl.asyncHttpClient;

@Service
public class FlightService {

    private static final Logger logger = LoggerFactory.getLogger(FlightService.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    @Value("${rapidapi.key}")
    private String apiKey;

    @Value("${rapidapi.host}")
    private String apiHost;

    public List<Flight> getFlights(String originCity, String destinationCity, String date) {
        try (AsyncHttpClient client = asyncHttpClient()) {
            String departureId = getAirportCode(client, originCity);
            String arrivalId = getAirportCode(client, destinationCity);

            if (departureId == null || arrivalId == null) {
                logger.warn("Could not resolve airport codes for inputs: {} -> {}", originCity, destinationCity);
                return List.of();
            }

            String url = String.format(
                    "https://%s/price-graph/for-one-way?departureId=%s&arrivalId=%s&departureDate=%s",
                    apiHost, departureId, arrivalId, date
            );

            Response response = withApiHeaders(client.prepare("GET", url))
                    .execute()
                    .toCompletableFuture()
                    .join();

            JsonNode root = mapper.readTree(response.getResponseBody());
            JsonNode calendar = root.path("data").path("calendar");

            List<Flight> flights = new ArrayList<>();

            if (calendar.isArray()) {
                for (JsonNode entry : calendar) {
                    String flightDate = entry.path("date").asText("");
                    String price = entry.path("price").asText("N/A");

                    flights.add(new Flight(flightDate, originCity, destinationCity, price));
                }
            }

            return flights;

        } catch (Exception e) {
            logger.error("Error fetching flights", e);
            return List.of();
        }
    }

    private String getAirportCode(AsyncHttpClient client, String cityName) {
        try {
            String queryUrl = String.format(
                    "https://%s/auto-complete?query=%s",
                    apiHost, URLEncoder.encode(cityName, StandardCharsets.UTF_8)
            );

            Response response = withApiHeaders(client.prepare("GET", queryUrl))
                    .execute()
                    .toCompletableFuture()
                    .join();

            JsonNode root = mapper.readTree(response.getResponseBody());
            JsonNode data = root.path("data");

            if (!data.isEmpty()) {
                return data.get(0).path("code").asText(); // IATA code
            } else {
                logger.warn("No airport code found for city: {}", cityName);
                return null;
            }

        } catch (Exception e) {
            logger.error("Failed to get airport code for city: {}", cityName, e);
            return null;
        }
    }

    private BoundRequestBuilder withApiHeaders(BoundRequestBuilder request) {
        return request
                .setHeader("x-rapidapi-key", apiKey)
                .setHeader("x-rapidapi-host", apiHost);
    }
}