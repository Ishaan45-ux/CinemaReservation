package com.example.ticket_booking;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Tells Spring to create a database table for this class
@Table(name = "users") // We name the table "users" because "user" is often a reserved word in SQL
@Data // Lombok: Auto-generates Getters, Setters, and toString methods
@NoArgsConstructor // Lombok: Auto-generates an empty constructor
@AllArgsConstructor // Lombok: Auto-generates a full constructor
public class User {

    @Id // This is the Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increments (1, 2, 3...)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Column(nullable = false)
    private String fullName;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Please provide a valid email address")
    @Column(unique = true, nullable = false) // Ensures no duplicate accounts
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Column(nullable = false)
    private String password;

    // This will help us later with Spring Security (e.g., ROLE_USER vs ROLE_ADMIN)
    @Column(nullable = false)
    private String role = "ROLE_USER"; 
}
