package com.example.ticket_booking.booking;

import com.example.ticket_booking.Showtime.Showtime;
import com.example.ticket_booking.Showtime.ShowTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class BookingController {

    @Autowired
    private ShowTimeRepository showtimeRepository;

    @Autowired
    private TicketRepository ticketRepository;
    
 // 1. Make sure this is at the top of your class with your other @Autowired variables
    @Autowired
    private com.example.ticket_booking.User.UserRepository userRepository;

    @GetMapping("/seats/{showtimeId}")
    public String showSeatSelection(@PathVariable Long showtimeId, Model model) {
        
        Showtime showtime = showtimeRepository.findById(showtimeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid showtime"));

        // 1. Fetch all tickets already sold for this showtime
        List<Ticket> soldTickets = ticketRepository.findByShowtimeId(showtimeId);
        
        // 2. Extract the seat strings (e.g. "A1,A2") and split them into individual seats
        List<String> bookedSeats = new ArrayList<>();
        for (Ticket t : soldTickets) {
            String[] seats = t.getSeatNumbers().split(",");
            bookedSeats.addAll(Arrays.asList(seats));
        }

        // 3. Pass data to the HTML page
        model.addAttribute("showtime", showtime);
        model.addAttribute("bookedSeats", bookedSeats);
        
        // We pass rows and columns to Thymeleaf so it can draw a dynamic 6x10 grid without writing 60 lines of HTML
        model.addAttribute("rows", Arrays.asList("A", "B", "C", "D", "E", "F"));
        model.addAttribute("cols", Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        return "seat-selection"; 
    }
    
 // 2. Add this new method anywhere inside BookingController
    @GetMapping("/my-tickets")
    public String viewMyTickets(Model model, java.security.Principal principal) {
        // If the user isn't logged in, send them to login
        if (principal == null) {
            return "redirect:/login";
        }

        // Find the user by their email (principal.getName() gets the logged-in email)
        com.example.ticket_booking.User.User currentUser = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch only their CONFIRMED tickets
        List<Ticket> myTickets = ticketRepository.findByUserAndStatusOrderByIdDesc(currentUser, "CONFIRMED");
        
        model.addAttribute("tickets", myTickets);
        return "my-tickets";
}
}