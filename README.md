# Micro-Alertas - Microservicio de Alertas

Microservicio Spring Boot para la gestión de alertas de incendios. Permite filtrar por severidad, tipo y fecha.

## Tecnologías

- Spring Boot 3.3.4, Spring Data JPA, Spring Validation, Spring Cloud
- PostgreSQL (AWS RDS), H2 (tests)
- Apache Kafka
- JaCoCo, Mockito, JUnit 5, Springdoc OpenAPI

## Endpoints

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | `/api/alerts` | Listar alertas (filtros: severity, fireType, date) |
| GET | `/api/alerts/{id}` | Obtener alerta por ID |

Swagger: `http://localhost:8083/swagger-ui/index.html`

## Ejecutar

```bash
.\mvnw.cmd spring-boot:run
```

## Pruebas

```bash
.\mvnw.cmd test        # ejecutar tests
.\mvnw.cmd verify      # tests + JaCoCo report
```

Cobertura: **100%**

## Capturas

<img width="915" height="140" alt="image" src="https://github.com/user-attachments/assets/c553b117-ad91-4b26-a9a6-a0e104829b33" />

