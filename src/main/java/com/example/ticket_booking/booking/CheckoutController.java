package com.example.ticket_booking.booking;

import com.example.ticket_booking.Showtime.Showtime;
import com.example.ticket_booking.Showtime.ShowTimeRepository;
import com.example.ticket_booking.User.User;
import com.example.ticket_booking.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CheckoutController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ShowTimeRepository showtimeRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/checkout")
    public String processCheckout(@RequestParam Long showtimeId, 
                                  @RequestParam(required = false) String selectedSeats, 
                                  Model model) {
        
        // 1. If they didn't select any seats, kick them back
        if (selectedSeats == null || selectedSeats.isEmpty()) {
            return "redirect:/seats/" + showtimeId + "?error=NoSeatsSelected";
        }

        // 2. Fetch the current logged-in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepository.findByEmail(auth.getName())
            .orElseThrow(() -> new RuntimeException("User not found"));

        Showtime showtime = showtimeRepository.findById(showtimeId).orElseThrow();

        // 3. Calculate total price (number of seats * price per seat)
        int seatCount = selectedSeats.split(",").length;
        Double totalAmount = seatCount * showtime.getFinalPrice();

        // 4. Create the PENDING ticket lock in the database
        Ticket pendingTicket = new Ticket();
        pendingTicket.setUser(currentUser);
        pendingTicket.setShowtime(showtime);
        pendingTicket.setSeatNumbers(selectedSeats);
        pendingTicket.setTotalAmount(totalAmount);
        pendingTicket.setStatus("PENDING"); // Temporary hold
        
        ticketRepository.save(pendingTicket);

        // 5. Send them to the payment page with the ticket ID
        model.addAttribute("ticket", pendingTicket);
        return "payment"; 
    }

    // Catches the submission from the payment form
    @PostMapping("/process-payment")
    public String finalizePayment(@RequestParam Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow();
        ticket.setStatus("CONFIRMED"); // Payment success! Lock is permanent.
        ticketRepository.save(ticket);
        
        return "redirect:/?bookingSuccess"; // Send them home with a success message
    }
}