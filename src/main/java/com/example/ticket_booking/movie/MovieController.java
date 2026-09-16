package com.example.ticket_booking.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    // Shows the form
    @GetMapping("/add-movie")
    public String showAddMovieForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "add-movie"; // Looks for add-movie.html
    }

    // Catches the submitted form data
    @PostMapping("/add-movie")
    public String addMovie(@ModelAttribute("movie") Movie movie) {
        movieService.saveMovie(movie);
        return "redirect:/add-movie?success"; // Reloads the page with a success flag
    }
    
    @PostMapping("/delete-movie/{id}")
    public String deleteMovie(@PathVariable Long id) {
        // Call the service method instead
        movieService.deleteMovieById(id);
        return "redirect:/?deleted"; 
    }
}