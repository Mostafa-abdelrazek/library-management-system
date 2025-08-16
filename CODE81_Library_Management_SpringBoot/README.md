# CODE81 Library Management System (Spring Boot)

A complete reference implementation for the CODE81 challenge.

## Tech Stack
- Java 17, Spring Boot 3.3 (Web, Data JPA, Security)
- H2 in-memory DB (replace with Postgres/MySQL as needed)
- OpenAPI/Swagger UI
- Role-Based Access Control (ADMIN, LIBRARIAN, STAFF)
- Basic Auth + BCrypt password storage
- User activity logging filter
- ERD (mermaid) in `docs/ERD.md`
- SQL seed data in `src/main/resources/data.sql`
- Postman collection in `postman/LibraryAPI.postman_collection.json`

## How to Run (Maven)
```bash
mvn spring-boot:run
```
Swagger UI: http://localhost:8080/swagger-ui.html  
H2 Console: http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:mem:librarydb`)

Default Users (auto-created on startup):
- admin / admin123 (ROLE_ADMIN)
- librarian / librarian123 (ROLE_LIBRARIAN)
- staff / staff123 (ROLE_STAFF)

## Import into Eclipse
1. **File → Import → Maven → Existing Maven Projects**.
2. Select the project root folder.
3. Finish. Make sure you have **JDK 17** configured.
4. Optional: install Lombok plugin if you enable Lombok later (not required here).

## API Quickstart
Use the Postman collection provided. All endpoints require Basic Auth.
- `GET /api/books` list books
- `POST /api/librarian/transactions/borrow` borrow
- `POST /api/librarian/transactions/return` return
- `POST /api/admin/users` manage users (ADMIN only)

## Design Notes
- Rich book metadata (multi-author/category, publisher, language, edition, year, ISBN, summary, cover)
- Hierarchical categories via self-referencing `Category.parent`
- Borrow logic updates `availableCopies`
- Return logic sets `status` to RETURNED or OVERDUE based on `dueAt`
- Activity logging persisted for auditing
