# SHOPEX

---

Shop API project for exercise different spring, java features and good practises - check them in action.
 
---

Tech stack:
  - Java 23
    - Lombok
    - Swagger-ui
  - PostgreSQL
  - Docker
    - Dockerfile
    - docker compose configuration
      - setting environment variables for _application.properties_
  - GitHub workflows for CI/CD
  - pgadmin

Features:
  - Records
  - Integration with external API - https://api.zippopotam.us/
    - using @HttpExchange
  - REST
    - DTOs
    - @RequestParam
    - validation.annotation.Validated
    - validation.constraints.Pattern
    - Pageable
  - ApplicationExceptionsHandler
  - Tests:
    - Mockito usage 
    - Unit
    - Integration

