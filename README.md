# Store Management Application

This is a simple Spring Boot application for managing products, with **user** and **admin** roles.  
It uses **Spring Security** for authentication and authorization, **Spring Data JPA** for persistence, and an **H2 database**.

---

## Features

- User roles: `USER` and `ADMIN`
- CRUD operations on products
- Validation for product fields
- Exception handling via `GlobalExceptionHandler`
- In-memory authentication for simplicity

---

## How to use the application

###Build and run using Maven
mvn clean spring-boot:run

### API endpoints for USER
- GET /api/products        # List all products
- GET /api/products/{id}   # Get product by id

### API endpoints for ADMIN
- POST /admin/products             # Add product
- PUT /admin/products/{id}/price   # Update product price
- PUT /admin/products/{id}/name    # Update product name
- DELETE /admin/products/{id}      # Delete product

### cURL request example
- curl.exe -u user:userpass http://localhost:8080/api/products
- curl.exe -u user:userpass http://localhost:8080/api/products/1
- curl.exe -X POST -u admin:adminpass -H "Content-Type: application/json" -d '{\"name\":\"Laptop\",\"price\":1200}' http://localhost:8080/admin/products
- curl.exe -X PUT -u admin:adminpass -H "Content-Type: application/json" -d '{\"name\":\"LaptopNewName\"}' http://localhost:8080/admin/products/1/name
- curl.exe -X PUT -u admin:adminpass -H "Content-Type: application/json" -d '{\"price\":1500}' http://localhost:8080/admin/products/1/price
- curl.exe -X DELETE -u admin:adminpass -H "Content-Type: application/json"  http://localhost:8080/admin/products/1
