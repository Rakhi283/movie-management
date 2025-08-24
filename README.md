# Movie Management API

The Movie Management System is a RESTful Spring Boot application that allows users to manage movies in a MySQL database. It provides CRUD (Create, Read, Update, Delete) operations with validation, exception handling, and Swagger/OpenAPI documentation.

## Features

- Add, update, delete, and retrieve movies
- Input validation using Jakarta Bean Validation
- Exception handling with custom error responses
- API documentation via Swagger UI (springdoc-openapi)
- Uses ModelMapper for DTO/entity conversion
- MySQL database integration with JPA/Hibernate

## Project Structure

```
src/
  main/
    java/com/Zymr/assessment/
      controller/         # REST controllers
      dto/                # Data Transfer Objects (DTOs)
      entites/            # JPA entities
      repositories/       # Spring Data JPA repositories
      service/            # Business logic/services
      configuration/      # Bean configuration (ModelMapper)
      ExceptionHandler/   # Custom exceptions & global handler
    resources/
      application.properties  # Spring Boot & DB config
  test/
    java/com/Zymr/assessment/
      MovieManagementApiApplicationTests.java  # Basic context test
```

## Technologies Used

- Java 21
- Spring Boot 3.5.5
- Spring Data JPA
- Spring Validation
- MySQL
- Lombok
- ModelMapper
- Swagger/OpenAPI (springdoc-openapi)
- JUnit 5

## Getting Started

### Prerequisites

- Java 21+
- Maven
- MySQL running on `localhost:3306` (default DB: `MovieDB`)

### Configuration

Edit `src/main/resources/application.properties` for your DB credentials:

```properties
spring.application.name=Movie Management API
spring.jpa.hibernate.ddl-auto=update
spring.DataSource.url=jdbc:mysql://localhost:3306/MovieDB?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.DataSource.username=root
Spring.DataSource.password=MySQLRakhi424@
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show.sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### Build & Run

```sh
./mvnw spring-boot:run
```
Or, if using Windows:
```sh
mvnw.cmd spring-boot:run
```

### API Endpoints

| Method | Endpoint         | Description          |
|--------|------------------|----------------------|
| POST   | `/movies`        | Create a new movie   |
| GET    | `/movies/{id}`   | Get movie by ID      |
| GET    | `/movies`        | List all movies      |
| PUT    | `/movies/{id}`   | Update movie by ID   |
| DELETE | `/movies/{id}`   | Delete movie by ID   |

### Swagger UI

After starting the app, access API docs at:  
`http://localhost:8080/swagger-ui/index.html`

## Exception Handling

Custom exceptions are handled globally via [`RestResponseEntityExceptionHandler`](src/main/java/com/Zymr/assessment/ExceptionHandler/RestResponseEntityExceptionHandler.java), returning structured error responses.

## Testing

Run tests with:

```sh
./mvnw test
```

## License

Licensed under the Apache License, Version 2.0.

---

For more details, see the source code in [src/main/java/com/Zymr/assessment/](src/main/java/com/Zymr/assessment/)
