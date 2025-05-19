package edu.bekthedev.diamondstravel.service;

import edu.bekthedev.diamondstravel.model.Flight;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//  Mockito
@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {

    // Mock the FlightService
    @Mock
    private FlightService flightService;

    // Create mock data for flights
    @Test
    public void testGetFlights() {

        List<Flight> mockFlights = List.of(
                new Flight("2025-05-20", "New York", "Los Angeles", "300"),
                new Flight("2025-05-21", "New York", "Chicago", "200")
        );


        when(flightService.getFlights("New York", "Los Angeles", "2025-05-20")).thenReturn(mockFlights);


        List<Flight> flights = flightService.getFlights("New York", "Los Angeles", "2025-05-20");


        assertEquals(2, flights.size());
        assertEquals("2025-05-20", flights.get(0).getDepartureDate());
        assertEquals("300", flights.get(0).getPrice());
    }
}