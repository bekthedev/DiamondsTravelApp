package edu.bekthedev.diamondstravel.controller;

import edu.bekthedev.diamondstravel.model.Flight;
import edu.bekthedev.diamondstravel.service.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FlightSearchController {


    private final FlightService flightService;

    public FlightSearchController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping({"/", "/index"})
    public String showSearchForm() {
        return "index";
    }

    @PostMapping("/search")
    public String searchFlights(@RequestParam String origin,
                                @RequestParam String destination,
                                @RequestParam String date,
                                Model model) {
        // Get the flight data from the service
        List<Flight> flights = flightService.getFlights(origin, destination, date);
        // Debugging: Print the flights to the console to check their content
        System.out.println("Flights: " + flights);

        // Add the flight data to the model
        model.addAttribute("flights", flights);

        // Return the results view
        return "results";
    }



    @GetMapping("/login")
    public String login() {
        return "login";
    }
}