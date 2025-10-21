# Mdotm.ai Java Assignment — Pets Service

A Spring Boot 3 (Java 17) REST API implementing a simple CRUD for *Pets*, 
designed with clean architecture principles and documented via OpenAPI (Swagger UI).

## Overview
- CRUD endpoints under `/pets`
- Paging, sorting, and filters (`species`, `owner` contains)
- Validation (Bean Validation)
- RFC-7807 ProblemDetails for errors
- H2 in-memory DB (no installation)
- Domain model independent from persistence (future NoSQL-ready)

## Tech Stack
- Java 17, Spring Boot 3.3.4
- Spring Data JPA, H2
- Lombok
- springdoc-openapi + Swagger UI
- JUnit 5

## 🚀 Run locally
```bash
mvn clean spring-boot:run
```
Open:
- Swagger UI → http://localhost:8080/swagger-ui.html
- OpenAPI JSON → http://localhost:8080/v3/api-docs
- H2 Console → http://localhost:8080/h2-console (JDBC: `jdbc:h2:mem:petsdb`)

## 🐾 API Endpoints

### 1) Create a new Pet
**POST** `/pets`
```json
{
  "name": "Fido",
  "species": "Dog",
  "age": 3,
  "ownerName": "Alice Johnson"
}
```
Responses: **201 Created**, **400 Bad Request**

### 2) Get a Pet by ID
**GET** `/pets/{id}`  
Responses: **200 OK**, **404 Not Found**

### 3) Update an existing Pet
**PUT** `/pets/{id}`
```json
{
  "name": "Buddy",
  "species": "Dog",
  "age": 4,
  "ownerName": "Alice Johnson"
}
```
Responses: **200 OK**, **400 Bad Request**, **404 Not Found**

### 4) Delete a Pet
**DELETE** `/pets/{id}`  
Responses: **204 No Content**, **404 Not Found**

### 5) List Pets with filters, paging, sorting
**GET** `/pets`
Query params: `species`, `owner`, `page`, `size`, `sort`  
Example: `/pets?species=Dog&owner=ali&page=0&size=5&sort=createdAt,desc`

Example response:
```json
{
  "page": 0,
  "size": 5,
  "totalElements": 2,
  "totalPages": 1,
  "items": [ { /* PetResponse */ } ]
}
```

## 🧱 Project Structure
```
src/main/java/ai/mdotm/assignment/pets
├── PetsApplication.java
├── domain/
│   ├── Pet.java
│   └── PetRepository.java
├── application/
│   ├── PetService.java
│   └── PetNotFoundException.java
├── infrastructure/jpa/
│   ├── PetEntity.java
│   ├── JpaPetRepository.java
│   └── PetRepositoryJpaAdapter.java
├── web/
│   ├── PetController.java
│   ├── PetMapper.java
│   ├── GlobalExceptionHandler.java
│   └── dto/
│       ├── PetRequest.java
│       ├── PetResponse.java
│       └── PagedResponse.java
└── config/
    └── OpenApiConfiguration.java
```

## ✅ Notes
- Lombok requires annotation processing (enabled via Maven, and ideally in IDE).
- IDs are UUID strings for portability.
- Schema is auto-created (`ddl-auto=update`).

— Implemented by **Giorgio Segatori** for **Mdotm.ai**.
