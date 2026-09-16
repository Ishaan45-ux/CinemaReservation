package com.example.ticket_booking.booking;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    
    // Grabs every ticket ever purchased for a specific showtime
    List<Ticket> findByShowtimeId(Long showtimeId);
 // Finds tickets that have a specific status AND were created before a specific time
    List<Ticket> findByStatusAndBookingTimeBefore(String status, java.time.LocalDateTime time);
 // Add this inside TicketRepository interface
    List<Ticket> findByUserAndStatusOrderByIdDesc(com.example.ticket_booking.User.User user, String status);
}