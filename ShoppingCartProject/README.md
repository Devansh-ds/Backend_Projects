# Shopping Cart Project

This document provides an overview of a Shopping Cart application, detailing its features, technologies, and setup instructions.

## Overview

The Shopping Cart project is a monolithic application focused on Spring Data JPA for database interaction. It is designed to manage products, categories, orders, users, and shopping carts. The application currently does not include authentication, making it ideal for further extension and customization.

### Description

A full-stack Shopping Cart application to manage products, categories, orders, users, and cart items. It is designed for easy integration with various frontend frameworks and includes robust backend support with Spring Boot and MySQL.

- **Features:**

  - Product and category management
  - User management (authentication pending implementation)
  - Cart and order processing
  - Image association with products

- **Technologies:**
  - Backend: Spring Boot, Spring Data JPA
  - Database: MySQL
  - ORM: Hibernate
  - Build Tool: Maven

---

## Features

### 1. Product Management

- Add, update, delete, and list products.
- Associate products with categories.

### 2. Category Management

- Organize products into categories.
- Manage category hierarchy.

### 3. User Management

- Store and manage user information (authentication not yet implemented).

### 4. Shopping Cart

- Add and remove items to/from the cart.
- Maintain cart items and quantities for each user.

### 5. Order Processing

- Place orders from cart items.
- Track order history.

### 6. Image Management

- Associate images with products.
- Store image metadata.

---

## Technologies

- **Backend**: Spring Boot, Spring Data JPA
- **Database**: MySQL
- **Frontend**: (Not yet implemented, suitable for integration with any frontend framework)
- **ORM**: Hibernate
- **Build Tool**: Maven

---

## Setup and Installation

### Prerequisites

- Java 17+
- Maven
- MySQL

### Steps

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/Devansh-ds/Backend_Projects/tree/main/ShoppingCartProject
   cd shopping-cart
   ```

2. **Configure the Database**:

   - Create a MySQL database (e.g., `shopping_cart`).
   - Update the `application.properties` file with your database credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/shopping_cart
     spring.datasource.username=<your-username>
     spring.datasource.password=<your-password>
     spring.jpa.hibernate.ddl-auto=update
     ```

3. **Build the Project**:

   ```bash
   mvn clean install
   ```

4. **Run the Application**:

   ```bash
   mvn spring-boot:run
   ```

5. **Access the Application**:
   - APIs will be accessible at `http://localhost:8080`.
   - Use tools like Postman to test the endpoints.

---

## Future Improvements (not yet implemented)

- Implement authentication and authorization using Spring Security.
- Develop a frontend for better user interaction.
- Add support for payment processing.
- Integrate caching for enhanced performance.
- Create detailed API documentation with Swagger.

---

## Contact

For further details or issues, feel free to reach out to me at Devanshsingla@gmail.com
