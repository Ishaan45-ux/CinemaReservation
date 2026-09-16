package com.example.ticket_booking.Showtime;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowTimeRepository extends JpaRepository<Showtime, Long> {
    
    // We will use this to find all theatres playing a specific movie when a user clicks "Select Theatre"
    List<Showtime> findByMovieId(Long movieId);
}
