package edu.bekthedev.diamondstravel.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightTest {

    @Test
    public void testFlightConstructorAndGetters() {
        //  constructor
        Flight flight = new Flight("2025-05-20", "New York", "Los Angeles", "300");

        assertEquals("2025-05-20", flight.getDepartureDate());
        assertEquals("New York", flight.getOrigin());
        assertEquals("Los Angeles", flight.getDestination());
        assertEquals("300", flight.getPrice());
    }

    @Test
    public void testFlightSetters() {
        //  Create  Flight
        Flight flight = new Flight();
        flight.setDepartureDate("2025-06-15");
        flight.setOrigin("Chicago");
        flight.setDestination("San Francisco");
        flight.setPrice("400");


        assertEquals("2025-06-15", flight.getDepartureDate());
        assertEquals("Chicago", flight.getOrigin());
        assertEquals("San Francisco", flight.getDestination());
        assertEquals("400", flight.getPrice());
    }
}