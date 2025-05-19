package edu.bekthedev.diamondstravel.controller;

import edu.bekthedev.diamondstravel.model.Booking;
import edu.bekthedev.diamondstravel.model.Flight;
import edu.bekthedev.diamondstravel.service.BookingService;
import edu.bekthedev.diamondstravel.service.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class FlightSearchController {

    private final FlightService flightService;
    private final BookingService bookingService;

    //  Constructor for services
    public FlightSearchController(FlightService flightService, BookingService bookingService) {
        this.flightService = flightService;
        this.bookingService = bookingService;
    }

    @GetMapping({"/", "/index"})
    public String showLandingPage() {
        return "index";
    }

    @GetMapping("/search")
    public String showSearchForm() {
        return "search";
    }

    @PostMapping("/search")
    public String searchFlights(@RequestParam String origin,
                                @RequestParam String destination,
                                @RequestParam String date,
                                Model model,
                                Principal principal) {

        // Save the booking into the database
        Booking booking = new Booking(principal.getName(), origin, destination, date);
        bookingService.saveBooking(booking);

        // Retrieve flights from service and add to model
        List<Flight> flights = flightService.getFlights(origin, destination, date);
        model.addAttribute("flights", flights);

        return "results";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }
}