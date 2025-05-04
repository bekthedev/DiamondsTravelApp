package edu.bekthedev.diamondstravel.controller;

import edu.bekthedev.diamondstravel.service.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("flights", flightService.getFlights(origin, destination, date));
        return "results";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}