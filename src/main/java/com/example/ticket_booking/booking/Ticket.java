package com.example.ticket_booking.booking;

import com.example.ticket_booking.User.User;
import com.example.ticket_booking.Showtime.Showtime;
import jakarta.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "showtime_id", nullable = false)
    private Showtime showtime;

    // We will save seats like this: "A1,A2,A3"
    @Column(nullable = false)
    private String seatNumbers;

    @Column(nullable = false)
    private Double totalAmount;
    
 // Add these right below your totalAmount variable
    @Column(nullable = false)
    private String status = "PENDING"; // Can be "PENDING", "CONFIRMED", or "CANCELLED"

    @Column(nullable = false)
    private java.time.LocalDateTime bookingTime = java.time.LocalDateTime.now();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Showtime getShowtime() {
		return showtime;
	}

	public void setShowtime(Showtime showtime) {
		this.showtime = showtime;
	}

	public String getSeatNumbers() {
		return seatNumbers;
	}

	public void setSeatNumbers(String seatNumbers) {
		this.seatNumbers = seatNumbers;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public java.time.LocalDateTime getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(java.time.LocalDateTime bookingTime) {
		this.bookingTime = bookingTime;
	}

    // ⚠️ CRITICAL: Right-click -> Source -> Generate Getters and Setters for ALL variables!
    // ⚠️ CRITICAL: Right-click -> Source -> Generate Constructors!
    
    
    
}
