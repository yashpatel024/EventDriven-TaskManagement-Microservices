# Event-Driven Microservice-based Task Management System

## Project Overview

This project implements a Task Management System using an event-driven microservices architecture. It leverages Spring Boot for the microservices, PostgreSQL for data storage, Apache Kafka for event streaming, and Docker for containerization.

## Architecture

The system consists of the following components:

- User Service: Manages user-related operations
- Task Service: Handles task creation, updates, and deletions
- Notification Service: Processes events and sends notifications
- Kafka: Facilitates event-driven communication between services
- Zookeeper: Manages the Kafka cluster
- Kafka UI: Provides a web interface for Kafka management

### System Architecture Diagram
WILL BE PUBLISHED LATER

## Technologies Used

- Java 11+
- Spring Boot
- Apache Kafka
- PostgreSQL
- Docker

## Getting Started

### Prerequisites

- Docker and Docker Compose
- Java JDK 11 or later
- Maven

### Setup

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/task-management-system.git
   cd task-management-system
   ```

2. Start the Docker containers:
   ```
   docker-compose up -d
   ```

3. Verify that all containers are running:
   ```
   docker-compose ps
   ```

4. Access Kafka UI at `http://localhost:8080` to manage Kafka topics and monitor the cluster.

### Configuration

Each microservice uses an `application.properties` file for configuration. Here's an example for Kafka configuration:

```properties
# Kafka Configuration
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.consumer.group-id=notification-service-group
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer
```

## Running the Application

1. Start each microservice (User, Task, Notification) using your IDE or command line.

2. Test API Endpoins mentioned below for specific request
   
## API Endpoints

- User Service: `http://localhost:8081/api/users`
- Task Service: `http://localhost:8082/api/tasks`
- Notification Service: `http://localhost:8083/api/notifications`

(Replace the ports with the actual ports you're using for each service)
