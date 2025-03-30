# VixHR - HR SAAS Solution

VixHR is a microservices-based HR SAAS solution developed by Microweb Global, designed to manage employees, track attendance with geolocation, calculate payroll, and more.

## Project Structure

This is a multi-module Spring Boot project consisting of:

- API Gateway
- Authentication Service
- Employee Service
- Attendance Service
- Payroll Service
- Notification Service
- Reporting Service
- Common Module (shared code)

## Prerequisites

- Java 17
- Docker and Docker Compose
- PostgreSQL
- Apache Kafka

## Getting Started

1. Clone the repository:
2. Build the project:
3. Start the services using Docker Compose:
4. The API Gateway will be available at: http://localhost:8080

## Development

Each service runs independently and communicates through REST APIs and Kafka events.

## Services

- **API Gateway** - Entry point for client requests (port 8080)
- **Auth Service** - Handles authentication and authorization (port 8081)
- **Employee Service** - Manages employee data (port 8082)
- **Attendance Service** - Tracks clock in/out times with geolocation (port 8083)
- **Payroll Service** - Calculates salaries, overtime, etc. (port 8084)
- **Notification Service** - Sends emails, SMS, and other notifications (port 8085)
- **Reporting Service** - Generates reports and analytics (port 8086)