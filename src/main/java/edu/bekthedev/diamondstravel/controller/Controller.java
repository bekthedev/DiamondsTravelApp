package edu.bekthedev.diamondstravel.controller;

import edu.bekthedev.diamondstravel.model.User;
import edu.bekthedev.diamondstravel.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    private final AuthService authService;

    public Controller(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        boolean valid = authService.authenticate(user.getUsername(), user.getPassword());
        return valid ? ResponseEntity.ok("Login successful") :
                ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        boolean success = authService.register(user);
        return success ? ResponseEntity.ok("User registered successfully") :
                ResponseEntity.status(400).body("Username already exists");
    }
}