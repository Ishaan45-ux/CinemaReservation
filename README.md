# Cinema Reservation Platform

A movie ticket booking platform built with Spring Boot and Thymeleaf. Currently
under active development.

## Tech Stack
- Java
- Spring Framework & Spring Boot
- Spring Security
- Spring Data JPA & Hibernate
- MySQL
- Thymeleaf

## Features (Current)
- Homepage implemented
- Login functionality (in progress)
- Data validation using Jakarta Validation to sanitize user inputs and prevent
  invalid database entries during registration
- Database persistence via Spring Data JPA, removing raw SQL queries and enabling
  rapid schema generation through Hibernate

## Architecture
Built using Spring Boot with Thymeleaf for server-side rendering, and Spring Data
JPA for database persistence.

## How to Run
1. Clone the repository
   ```bash
   git clone https://github.com/Ishaan45-ux/CinemaReservation
   ```
2. Configure your MySQL database credentials in `application.properties`
3. Build and run the application
   ```bash
   mvn spring-boot:run
   ```
4. Access the application at `http://localhost:8080`

## Status
🚧 Work in progress — login functionality and booking flow are being actively
built. Planned additions include seat selection, booking validation, and full
authentication.

## Author
Ishaan Ambare
