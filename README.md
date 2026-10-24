# 📚 Library Management API

![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Java](https://img.shields.io/badge/Java_21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)

## 📖 Project Overview

A RESTful Library Management API built with Spring Boot. It uses a layered architecture (Controller → Service → Repository) with a DTO layer and stateless **JWT authentication** (Spring Security, BCrypt password hashing) to manage the book inventory.

## 🚀 Tech Stack

* **Backend:** Java 21, Spring Boot 3.4
* **Security:** Spring Security, JWT (JJWT 0.12), BCrypt
* **Database:** Spring Data JPA, PostgreSQL (Docker) / H2 (tests)
* **Testing:** JUnit 5, Mockito
* **Tools:** Maven, Docker Compose, Swagger / OpenAPI (springdoc), GitHub Actions

---

## ⚙️ Setup and Installation

**Prerequisites:** JDK 21, Docker

**1. Clone the repository**
```bash
git clone https://github.com/Chabiiia/Library.git
cd Library
```

**2. Configure the database**
```bash
cp .env.example .env        # Windows PowerShell: Copy-Item .env.example .env
```
Edit `.env` and set a real `POSTGRES_PASSWORD`.

**3. Start PostgreSQL**
```bash
docker-compose up -d
```

**4. Set the JWT configuration** (the app will not start without these)

| Property | Env variable | Description |
|----------|--------------|-------------|
| `jwt.secret` | `JWT_SECRET` | HS256 signing key, at least 32 characters |
| `jwt.expiration` | `JWT_EXPIRATION` | Token lifetime in milliseconds (e.g. `3600000` = 1 hour) |

```bash
# Linux / macOS
export JWT_SECRET="replace-with-a-long-random-string-of-32+chars"
export JWT_EXPIRATION=3600000

# Windows PowerShell
$env:JWT_SECRET="replace-with-a-long-random-string-of-32+chars"
$env:JWT_EXPIRATION="3600000"
```

**5. Run the application**
```bash
./mvnw spring-boot:run      # Windows: mvnw.cmd spring-boot:run
```

## 📚 API Documentation

Swagger UI: `http://localhost:8080/swagger-ui.html`

## 🔐 Authentication

The API is secured with JWT. Register, log in, then send the token with every protected request:

```bash
# 1. Register
curl -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username": "john", "password": "secret123"}'

# 2. Log in and copy the token from the response
curl -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username": "john", "password": "secret123"}'

# 3. Call a protected endpoint
curl http://localhost:8080/books \
  -H "Authorization: Bearer <your-token>"
```

## 📍 API Endpoints

| Method | Endpoint | Description | Auth |
|--------|----------|-------------|------|
| POST | /auth/register | Register a new user | Public |
| POST | /auth/login | Log in and receive a JWT | Public |
| GET | /books | List all books | 🔒 |
| GET | /books/{id} | Get a book by ID | 🔒 |
| POST | /books | Add a new book | 🔒 |
| PUT | /books/{id} | Update a book | 🔒 |
| DELETE | /books/{id} | Delete a book | 🔒 |

## 🧪 Running Tests

```bash
./mvnw test
```
Tests run against an in-memory H2 database, so no Docker is needed.
