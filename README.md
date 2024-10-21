# Simple Web API

This is a simple web API developed using **Spring Boot 3**, **Java**, **JOOQ**, **PostgreSQL**, **Liquibase**, and **Gradle**.

## Tech Stack
- **Spring Boot 3**: Used as the framework to build and manage the web API.
- **Java 21**: Core programming language.
- **JOOQ**: Database access and query tool.
- **PostgreSQL**: Database system.
- **Liquibase**: For database version control.
- **Gradle**: Build tool.

## Architecture

This project follows the **Clean Architecture Pattern** (Ports & Adapters), organized into the following packages:

- **domain**: Contains the core domain logic and entities.
- **adapter/web**: Adapter for web-related concerns, handling HTTP requests.
- **adapter/jdbc**: Adapter for data persistence, utilizing JDBC with JOOQ.

---

This architecture ensures that core business logic remains independent of external dependencies, promoting flexibility and scalability.
