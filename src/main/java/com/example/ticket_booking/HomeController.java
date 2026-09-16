package com.example.ticket_booking;

import com.example.ticket_booking.movie.Movie;
import com.example.ticket_booking.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.*;
@Controller // Tells Spring this class serves web pages
public class HomeController {
	@Autowired
    private MovieService movieService; // Swap Repository for Service here too
    @GetMapping({"/", "/home"})
    public String showHomePage(Model model) {
        // Fetch all movies from the database
        List<Movie> allMovies = movieService.getAllMovies();
        
        // Attach the list to the model so the HTML file can see it
        model.addAttribute("movies", allMovies);
        
        return "index";
        }
}