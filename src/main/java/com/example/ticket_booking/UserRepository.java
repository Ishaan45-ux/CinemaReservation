package com.example.ticket_booking;

import com.example.ticket_booking.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    // Spring Data JPA is smart enough to write the SQL for this automatically!
    // We will need this specific method for the Login process.
    Optional<User> findByEmail(String email);
}