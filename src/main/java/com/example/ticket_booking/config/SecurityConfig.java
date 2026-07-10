package com.example.ticket_booking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Tells Spring this class contains setup instructions
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/","/home", "/register", "/css/**", "/js/**").permitAll() // Open these specific doors to everyone
                .anyRequest().authenticated() // Everything else requires a login
            ).formLogin(form -> form
                .loginPage("/login") // We will build this custom login page later
                .permitAll()
            );
            
        return http.build();
    }
}