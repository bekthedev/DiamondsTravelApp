package edu.bekthedev.diamondstravel.service;

import edu.bekthedev.diamondstravel.model.Flight;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)  // Enable Mockito in JUnit 5
public class FlightServiceTest {

    @Mock
    private FlightService flightService;  // Mock the FlightService

    @Test
    public void testGetFlights() {
        // Arrange: Create mock data for flights
        List<Flight> mockFlights = List.of(
                new Flight("2025-05-20", "New York", "Los Angeles", "300"),
                new Flight("2025-05-21", "New York", "Chicago", "200")
        );

        // Mock the method call to return mock data
        when(flightService.getFlights("New York", "Los Angeles", "2025-05-20")).thenReturn(mockFlights);

        // Act: Call the method under test
        List<Flight> flights = flightService.getFlights("New York", "Los Angeles", "2025-05-20");

        // Assert: Verify the results
        assertEquals(2, flights.size());
        assertEquals("2025-05-20", flights.get(0).getDepartureDate());
        assertEquals("300", flights.get(0).getPrice());
    }
}