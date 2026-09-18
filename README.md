# University Course & Enrollment Management System

A RESTful backend application for managing students, instructors, courses, and course enrollments.

Built with Java 25, Spring Boot 4, PostgreSQL, and Spring Data JPA. The project follows a layered architecture and is being developed incrementally to include validation, security, JWT authentication, testing, API documentation, and containerized deployment.

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 25 |
| Framework | Spring Boot 4 |
| Build Tool | Maven |
| Database | PostgreSQL |
| ORM | Spring Data JPA / Hibernate |
| Web | Spring Web MVC |
| Validation | Jakarta Bean Validation |
| Security | Spring Security 7 + JWT |
| JWT Library | JJWT |
| Testing | JUnit 5, Mockito, MockMvc |
| API Documentation | Swagger / OpenAPI |
| Deployment | Docker + Docker Compose |

## Features

### Implemented

- Student management
- Instructor management
- Course management
- Enrollment management
- JPA entity relationships
- Spring Data JPA repositories
- Service layer
- REST controllers
- PostgreSQL persistence

### Planned

- Request and response DTOs
- Manual entity/DTO mapping
- Request validation
- Global exception handling
- Standardized HTTP responses
- Pagination, sorting, and searching
- Transaction management
- Spring Security
- JWT authentication
- Role-based authorization
- Unit tests
- Integration tests
- Swagger / OpenAPI documentation
- Docker and Docker Compose support

## Domain Model

The main relationships are:

```text
Instructor ─────< Course

Student ─────< Enrollment >───── Course
