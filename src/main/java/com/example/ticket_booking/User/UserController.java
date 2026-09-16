package com.example.ticket_booking.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
@Controller // Tells Spring this class handles web requests and returns HTML pages
public class UserController {
	
	// We bring in the Service so we can actually save the data to MySQL
    @Autowired
    private UserService userService;
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
    
    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("user") User user) {
        
        // Let's print to the console just to prove the data arrived safely
        System.out.println("ATTEMPTING TO REGISTER USER: " + user.getEmail());
        
        // Hand the filled-out user object to the Service to be hashed and saved
        userService.registerUser(user);
        
        // Redirect them to the login page so they can log in
        return "redirect:/login?success"; 
    }
    
 // Add this inside your Controller
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Looks for login.html in the templates folder
    }
}

