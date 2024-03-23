# Prototype of IRCTC

## Overview
The Prototype of IRCTC is a web-based application designed to mimic the functionality of the Indian Railway Catering and Tourism Corporation (IRCTC) system. It provides users with the ability to register, login, search for trains, book tickets, and cancel tickets. The project is built using Spring Boot, Spring MVC, Hibernate, Thymeleaf, and MySQL, incorporating both client and server-side authentication for enhanced security.

## Features

### 1. Landing Page
- The landing page serves as an introduction to the IRCTC system, providing users with information about its features and services.

### 2. Registration
- Users are required to register before accessing the functionalities of the system.
- During registration, users can choose to register as either a regular user or an admin.
- Regular users receive an OTP for email verification, while admins receive an email for admin verification details.

### 3. Login
- Registered users can log in securely using their credentials.
- Password security is ensured using bCrypt in the backend.

### 4. Train Search Page
- Upon successful login, users are redirected to the train search page.
- Users can search for trains using two methods:
  - By providing the source, destination, and date to view all available trains on that particular date.
  - By entering a specific train ID to view the full route and details of that train.

### 5. Booking Tickets
- Users can book tickets for available seats on desired trains.
- Four types of seat categories are available: AC 1, AC 2, Sleeper, and General.

### 6. Ticket Cancellation
- Users have the option to cancel their booked tickets if needed.

### 7. Authentication
- The system implements both client and server-side authentication to ensure secure access to user accounts and data.

## Technologies Used
- **Spring Boot:** Provides a streamlined development environment for building Spring-based applications, offering features such as auto-configuration and embedded servers.
  
- **Spring MVC (Model-View-Controller):** A web framework within the Spring framework that facilitates the development of web applications by separating the application into three interconnected components - Model, View, and Controller.

- **Hibernate:** An object-relational mapping (ORM) framework for Java that simplifies database interactions by mapping Java objects to database tables and vice versa, abstracting away the complexities of SQL queries.

- **Thymeleaf:** A modern server-side Java template engine used for server-side rendering of HTML templates, enabling dynamic content generation in web applications.

- **MySQL:** A widely-used open-source relational database management system (RDBMS) known for its reliability, performance, and ease of use, utilized for storing and managing user data and application information.
