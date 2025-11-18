# System Design: Backend

## Architecture: Clean Architecture with Slices

This project utilizes a clean architecture approach, organized into "slices" for each feature. This promotes modularity, separation of concerns, and maintainability. Each slice contains all the necessary acomponents for a specific domain entity.

### Slice Structure

Each entity has its own dedicated folder (slice) with the following structure:

```
/src
  /main
    /java
      /com
        /app
          /features
            /entity_name
              ├── controller
              │   └── EntityNameController.java
              ├── service
              │   └── EntityNameService.java
              ├── repository
              │   └── EntityNameRepository.java
              ├── dto
              │   ├── EntityNameRequestDTO.java
              │   └── EntityNameResponseDTO.java
              ├── exception
              │   ├── EntityNameNotFoundException.java
              │   └── ... (other custom exceptions)
              └── model
                  └── EntityName.java
```

### Component Descriptions

*   **`controller`**: Contains the REST API endpoints. It receives HTTP requests, validates them, and calls the appropriate service methods.
*   **`service`**: Implements the business logic. It orchestrates data access and manipulation, and it's where the core application logic resides.
*   **`repository`**: Provides an abstraction over the database. It handles all the CRUD (Create, Read, Update, Delete) operations for the entity.
*   **`dto` (Data Transfer Object)**: Defines the data structures for API requests and responses. This helps to decouple the API from the internal data model.
*   **`exception`**: Contains custom exception classes for handling specific error scenarios.
*   **`model`**: Represents the domain entity, which is mapped to a database table.

## Example: `Course` Entity

As an example, let's consider a `Course` entity. The slice for this entity looks like this:

```
/src
  /main
    /java
      /com
        /app
          /features
            /course
              ├── controller
              │   └── CourseController.java
              ├── service
              │   └── CourseService.java
              ├── repository
              │   └── CourseRepository.java
              ├── dto
              │   ├── CourseRequestDTO.java
              │   └── CourseResponseDTO.java
              ├── exception
              │   └── CourseNotFoundException.java
              └── model
                  └── Course.java
```

This structure ensures that all the code related to the `Course` entity is organized in a single, cohesive module.
