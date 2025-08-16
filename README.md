# library-management-system
A Spring Boot Library Management System with role-based access control (ADMIN, LIBRARIAN, STAFF), book &amp; member management, borrow/return tracking, activity logging, Swagger API docs, and H2 database.
# 📚 Library Management System (Spring Boot)

A complete reference implementation of a Library Management System built with **Java 17** and **Spring Boot 3.3**.

## 🚀 Features
- Role-based access control (ADMIN, LIBRARIAN, STAFF)
- CRUD for Books, Members, Users
- Borrow & Return workflows (with overdue detection)
- Activity logging (user actions persisted)
- Swagger/OpenAPI UI
- H2 in-memory database with sample seed data
- REST APIs secured with Spring Security (Basic Auth)

## 🛠️ Tech Stack
- Java 17+
- Spring Boot 3.3
- Spring Data JPA (Hibernate)
- Spring Security (Basic Auth + BCrypt)
- H2 Database
- Swagger (springdoc-openapi)
- JUnit 5

## 🔑 Default Users
- **admin / admin123** → ROLE_ADMIN
- **librarian / librarian123** → ROLE_LIBRARIAN
- **staff / staff123** → ROLE_STAFF

## 📦 Running Locally
```bash
# Clone repo
git clone https://github.com/YOUR_USERNAME/library-management-system.git
cd library-management-system

# Run
mvn spring-boot:run
