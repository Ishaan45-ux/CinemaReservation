package com.example.ticket_booking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // 1. We keep the encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. We COMPLETELY DELETED the DaoAuthenticationProvider method. 
    // Spring will now build it automatically in the background!

    // 3. We keep our URL routing rules
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/", 
                    "/home", 
                    "/register", 
                    "/login", 
                    "/theatres/**", 
                    "/seats/**", 
                    "/css/**", 
                    "/js/**"
                ).permitAll()
                .requestMatchers(
                    "/add-movie", 
                    "/delete-movie/**", 
                    "/add-theatre", 
                    "/add-showtime"
                ).hasAuthority("ROLE_ADMIN")
                .requestMatchers(
                    "/checkout", 
                    "/process-payment"
                ).authenticated()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()
            );

        return http.build();
    }
}