# 📚 Library Management API

![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

## 📖 Project Overview
This project is a RESTful Library Management API developed using the Spring Boot framework. It follows clean code principles and a layered architecture (Controller-Service-Repository) to manage book inventories and user borrowing/returning processes smoothly.

## 🚀 Tech Stack
* **Backend:** Java 21, Spring Boot 3.x
* **Database:** Spring Data JPA, PostgreSQL (Dockerized) / H2 Database (Test environment)
* **Testing:** JUnit 5, Mockito (Unit Testing)
* **Tools:** Maven, Docker, Swagger (OpenAPI)

---

## ⚙️ Setup and Installation

**Step 1: Clone the repository**
```bash
git clone https://github.com/Chabiiia/Library.git
cd Library
```

**Step 2: Start the database**
```bash
docker-compose up -d
```

**Step 3: Build the application**
```bash
./mvnw clean install
```

**Step 4: Run the application**
```bash
./mvnw spring-boot:run
```

## 📚 API Documentation

Swagger UI: `http://localhost:8080/swagger-ui.html`

## 📍 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /books | List all books |
| GET | /books/{id} | Get a book by ID |
| POST | /books | Add a new book |
| PUT | /books/{id} | Update a book |
| DELETE | /books/{id} | Delete a book |

## 🧪 Running Tests

```bash
./mvnw test
```
