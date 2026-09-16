package com.example.ticket_booking.Showtime;

import com.example.ticket_booking.movie.Movie;
import com.example.ticket_booking.Theatre.Theatre;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "showtimes")
public class Showtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // This creates a Foreign Key linking to the movies table
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    // This creates a Foreign Key linking to the theatres table
    @ManyToOne
    @JoinColumn(name = "theatre_id", nullable = false)
    private Theatre theatre;

    @Column(nullable = false)
    private LocalDate showDate;

    @Column(nullable = false)
    private LocalTime showTime;

    // The final price for THIS specific theatre (Base price + Theatre Tax/Premium)
    @Column(nullable = false)
    private Double finalPrice;

    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}

	public LocalDate getShowDate() {
		return showDate;
	}

	public void setShowDate(LocalDate showDate) {
		this.showDate = showDate;
	}

	public LocalTime getShowTime() {
		return showTime;
	}

	public void setShowTime(LocalTime showTime) {
		this.showTime = showTime;
	}

	public Double getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Double finalPrice) {
		this.finalPrice = finalPrice;
	}
    
    
    // TODO: Right-click -> Source -> Generate Getters and Setters
	
	
    // TODO: Right-click -> Source -> Generate Constructors
    
}