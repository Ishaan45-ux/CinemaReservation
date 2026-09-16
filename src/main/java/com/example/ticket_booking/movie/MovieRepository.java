package com.example.ticket_booking.movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    
    // We can add custom search methods here later, like:
    // List<Movie> findByGenre(String genre);
}