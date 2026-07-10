package com.example.ticket_booking;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Tells Spring this class serves web pages
public class HomeController {

    // When a user visits localhost:8080/ or localhost:8080/home
    @GetMapping({"/", "/home"})
    public String showHomePage() {
        
        // This tells Spring to look inside src/main/resources/templates for a file named "index.html"
        return "index"; 
    }
}