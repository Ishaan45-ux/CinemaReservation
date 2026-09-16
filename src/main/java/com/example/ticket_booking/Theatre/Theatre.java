package com.example.ticket_booking.Theatre;

import jakarta.persistence.*;

@Entity
@Table(name = "theatres")
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // e.g., "PVR Cinemas"

    @Column(nullable = false)
    private String city; // e.g., "Bhopal"

    @Column(nullable = false)
    private String address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

    // TODO: Right-click -> Source -> Generate Getters and Setters
    // TODO: Right-click -> Source -> Generate Constructors
    
}