package com.example.ticket_booking.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service // Tells Spring to use this for business logic
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // Spring Security automatically calls this method when someone clicks "Login"
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        System.out.println("SPRING SECURITY IS SEARCHING DB FOR: " + email);
        
        // 1. Search our MySQL database for the email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    System.out.println("USER NOT FOUND IN DB!");
                    return new UsernameNotFoundException("User not found with email: " + email);
                });

        System.out.println("USER FOUND! Hashed DB Password is: " + user.getPassword());

        // 2. Translate our custom 'User' entity into Spring Security's official 'UserDetails' object
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(), 
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole()))
        );
    }
}