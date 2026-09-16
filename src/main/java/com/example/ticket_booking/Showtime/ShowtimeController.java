package com.example.ticket_booking.Showtime;

import com.example.ticket_booking.movie.Movie;
import com.example.ticket_booking.movie.MovieRepository;
import com.example.ticket_booking.Theatre.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ShowtimeController {

    @Autowired
    private ShowTimeRepository showtimeRepository;

    @Autowired
    private MovieRepository movieRepository;
    
    @Autowired
    private TheatreRepository theatreRepository;

    @GetMapping("/add-showtime")
    public String showAddShowtimeForm(Model model) {
        model.addAttribute("showtime", new Showtime());
        // Fetches movies and theatres from the DB so the HTML dropdowns can display them
        model.addAttribute("movies", movieRepository.findAll());
        model.addAttribute("theatres", theatreRepository.findAll());
        return "add-showtime"; // Looks for add-showtime.html
    }

    @PostMapping("/add-showtime")
    public String addShowtime(@ModelAttribute Showtime showtime) {
        showtimeRepository.save(showtime);
        return "redirect:/add-showtime?success";
    }
    // The {movieId} in the URL is dynamic, so clicking Movie #1 passes a 1 to this method
    @GetMapping("/theatres/{movieId}")
    public String showTheatres(@PathVariable Long movieId, Model model) {
        
        // 1. Fetch the movie to display its title at the top of the page
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid movie Id: " + movieId));
        
        // 2. Fetch all showtimes happening for this specific movie
        List<Showtime> showtimes = showtimeRepository.findByMovieId(movieId);
        
        // 3. Attach them to the model
        model.addAttribute("movie", movie);
        model.addAttribute("showtimes", showtimes);
        
        return "select-theatre"; // Looks for select-theatre.html
    }
}