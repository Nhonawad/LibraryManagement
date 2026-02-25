A Spring Boot REST API for managing library operations with user validations, book issuance (max 5 books/user),  and returns. In-memory storage for demo purposes.

Features
----------------------------------
User Management: Add users, list users
Book CRUD: Add, list available books
Book Issuance: Issue books with due dates (max 5 per user)
Validations: User/book existence checks, no-books-issued,book return overdue error handling
Returns: Return books

Tech Stack
-----------------------------------
Java 
Spring Boot 
Spring Web (REST)
In-memory HashMap storage

Clean Architecture: Separate service layer for all business logic


