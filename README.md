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

> _(Agregar captura del reporte JaCoCo y Swagger UI)_
