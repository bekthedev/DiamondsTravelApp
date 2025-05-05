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
        return "index";  // Show the flight search page
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
        return "results";  // Show the flight results
    }

    @GetMapping("/login")
    public String login() {
        return "login";  // Show the login page
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        // Simulate login logic (replace with actual authentication logic later)
        boolean loggedIn = simulateLogin(username, password);

        if (loggedIn) {
            return "redirect:/index";  // Redirect to the index page after successful login
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";  // Show error message if login fails
        }
    }

    private boolean simulateLogin(String username, String password) {
        // Simple check for fake credentials (replace with actual authentication logic)
        return "fakeuser".equals(username) && "password123".equals(password);
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";  // Show the registration page
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String confirmPassword, Model model) {
        // Simulate fake registration logic
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            return "register";  // Show error if passwords don't match
        }

        // Log the fake registration (optional, for debugging)
        System.out.println("Fake user registered: Username: fakeuser, Password: password123");

        // Redirect to the login page after "successful" registration
        return "redirect:/login";  // Redirect to the login page
    }
}