# Library management system
### Book Library Management with Spring Boot
This Spring Boot application provides a RESTful API for managing books, authors, and rentals in a library.

Features
- CRUD operations for Books and Authors
- Manage Book Rentals (create, retrieve)
- Retrieve books by Author
- Find available books for rent
- Identify overdue rentals

## Getting Started<br>
Prerequisites:
- Java 17
- Maven

1. Clone the repository<br>
Bash
- git clone https://your_repository_url.git

2. Install dependencies<br>
Navigate to the project directory and run:<br>
Bash
- mvn install

3. Configure database connection<br>
Update the application.properties file with your database connection details (e.g., driver class, URL, username, password).

4. Run the application<br>
Bash
- mvn spring-boot:run

<hr>

## ENDPOINTS

Books:
- GET/book/all
- GET/book/id
- GET/book?author=XYZ
- POST/book/add
- PUT/book/update/{id}
- DELETE//book/delete/{id}

Authors:
- GET/author/all
- GET/author/{id}
- POST/author/add
- PUT/author/update/{id}
- DELETE/author/delete/{id}

Rentals:
- GET/rent/all
- GET/rent/{id}
- POST/rent/new
- PUT/rent/return/{id}
- GET/rent/overdue
- GET/books/available



