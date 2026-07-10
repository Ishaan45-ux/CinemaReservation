package com.example.ticket_booking;

import com.example.ticket_booking.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Tells Spring this class handles web requests and returns HTML pages
public class UserController {
	// When a user makes a GET request to "/register" (typing it in the browser)
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        
        // We create an empty, blank User object
        User emptyUser = new User();
        
        // We attach this empty user to the 'Model' (the bridge between Java and HTML)
        model.addAttribute("user", emptyUser);
        
        // This tells Spring to look for an HTML file named "register.html"
        return "register"; 
    }
}

