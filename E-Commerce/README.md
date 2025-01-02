# Microservices Project

This document provides an overview of a microservices-based application, detailing its architecture, services, and functionalities.

## Overview

The project is designed using a microservices architecture, enabling scalability, modularity, and ease of development. It comprises the following services:

- **Product Service**: Manages products and categories.
- **Customer Service**: Handles customer information.
- **Order Service**: Processes product purchases and interacts with other services.
- **Payment Service**: Handles payment processing and confirmation.
- **Notification Service**: Sends email notifications to customers.
- **Config Server**: Manages and centralizes configuration.
- **API Gateway**: Provides a unified entry point and handles routing.
- **Eureka Server**: Acts as a service registry for service discovery.

---

## Services

### 1. Product Service

- **Responsibilities**:
  - Manage products and their categories.
  - Provide APIs for querying product details.
- **Technologies**:
  - Spring Boot
  - Spring Data JPA
  - MySQL

### 2. Customer Service

- **Responsibilities**:
  - Manage customer details.
  - Validate the existence of customers.
- **Technologies**:
  - Spring Boot
  - Spring Data JPA
  - MySQL

### 3. Order Service

- **Responsibilities**:
  - Handle product purchase requests.
  - Validate customer existence and product availability.
  - Interact with the Payment Service to process payments.
  - Send order confirmation via Apache Kafka.
- **Technologies**:
  - Spring Boot
  - Feign Client
  - Apache Kafka
  - MySQL

### 4. Payment Service

- **Responsibilities**:
  - Process payment requests.
  - Always accept payments (currently for development purposes).
  - Send payment confirmation to the Notification Service via Kafka.
- **Technologies**:
  - Spring Boot
  - Apache Kafka

### 5. Notification Service

- **Responsibilities**:
  - Act as a Kafka consumer.
  - Consume both payment and order confirmations.
  - Send email notifications to customers using JavaMailSender.
- **Technologies**:
  - Spring Boot
  - Apache Kafka
  - JavaMailSender

### 6. Config Server

- **Responsibilities**:
  - Centralize configuration management.
  - Fetch configurations from a GitHub repository.
- **Technologies**:
  - Spring Cloud Config
  - GitHub

### 7. API Gateway

- **Responsibilities**:
  - Route requests to appropriate services.
  - Provide a unified entry point for clients.
- **Technologies**:
  - Spring Cloud Gateway

### 8. Eureka Server

- **Responsibilities**:
  - Act as a service registry for dynamic service discovery.
- **Technologies**:
  - Spring Cloud Netflix Eureka

---

## Flow Diagram

1. **Order Service**:
   - Validates customer and product.
   - Calls Payment Service using Feign Client.
   - Publishes order confirmation to Kafka.
2. **Payment Service**:
   - Processes the payment.
   - Publishes payment confirmation to Kafka.
3. **Notification Service**:
   - Consumes both order and payment confirmations.
   - Sends email notifications to customers.

---

## Future Improvements

- Implement actual payment processing logic in Payment Service.
- Enhance security with Spring Security and OAuth2.
- Add monitoring and logging with tools like Prometheus and Grafana.
- Implement circuit breakers for better fault tolerance.

---

## Setup and Deployment

1. **Prerequisites**:

   - Java 17+
   - Maven
   - MySQL
   - Kafka Broker
   - GitHub repository for Config Server
   - Docker and Docker Compose

2. **Steps**:
   - Ensure Docker is installed and running.
   - Navigate to the project root directory containing the `docker-compose.yml` file.
   - Run the following command to start all services:
     ```bash
     docker-compose up -d
     ```
   - Verify the services are running:
     ```bash
     docker ps
     ```
   - Access the application via the API Gateway's exposed port.
   - Use tools like Postman to test the endpoints.
   - To stop the services, use:
     ```bash
     docker-compose down
     ```

---

## Contact

For further details or issues, feel free to reach out to me Devanshsingla@gmail.com
