package com.example.ticket_booking.movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Marks this as a business logic class
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    // Method to save a new movie to MySQL
    public void saveMovie(Movie movie) {
        movieRepository.save(movie);
    }
    
 // Add this new method
    public void deleteMovieById(Long id) {
        // Later on, we can add logic here to check for existing showtimes before deleting!
        movieRepository.deleteById(id);
    }
    // We will use this later to display all movies on the home page!
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
}