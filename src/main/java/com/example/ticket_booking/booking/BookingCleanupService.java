package com.example.ticket_booking.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingCleanupService {

    @Autowired
    private TicketRepository ticketRepository;

    // This tells Spring to run this exact method every 60 seconds (60,000 milliseconds)
    @Scheduled(fixedRate = 60000)
    public void cleanupAbandonedBookings() {
        
        // Find the exact time it was 10 minutes ago
        LocalDateTime tenMinutesAgo = LocalDateTime.now().minusMinutes(10);
        
        // Fetch all tickets that are still PENDING and older than 10 minutes
        List<Ticket> abandonedTickets = ticketRepository.findByStatusAndBookingTimeBefore("PENDING", tenMinutesAgo);
        
        if (!abandonedTickets.isEmpty()) {
            // Delete them to free the seats back up!
            ticketRepository.deleteAll(abandonedTickets);
            System.out.println("Freed up " + abandonedTickets.size() + " abandoned seat locks.");
        }
    }
}