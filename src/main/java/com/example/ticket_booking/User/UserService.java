package com.example.ticket_booking.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service // Tells Spring this class handles business logic
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(User user) {
        // 1. Take the plain-text password the user typed
        String plainPassword = user.getPassword();
        
        // 2. Hash it using BCrypt
        String hashedPassword = passwordEncoder.encode(plainPassword);
        
        // 3. Put the scrambled password back into the user object
        user.setPassword(hashedPassword);
        
        // 4. Force the role to be "ROLE_USER" just to be safe
        user.setRole("ROLE_USER");
        
        // 5. Save the user to the MySQL database!
        userRepository.save(user);
    }
}