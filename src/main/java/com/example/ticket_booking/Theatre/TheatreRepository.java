package com.example.ticket_booking.Theatre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {
    // Custom method to let users filter theatres by their city later
    List<Theatre> findByCity(String city);
}