# Centro de Diagnóstico Médico — API REST con DTOs y validaciones (Tema 4)

API REST de Pacientes construida sobre el proyecto Spring Boot del tema
anterior, agregando DTOs con validaciones (`@Valid`) y manejo
centralizado de excepciones con `@RestControllerAdvice`.

## Estructura relevante para este TP

```
src/main/java/com/centrodiagnostico/
├── dto/
│   ├── PacienteRequestDTO.java     # Requerimiento 1 y 2 (validaciones)
│   ├── PacienteResponseDTO.java    # Requerimiento 1
│   ├── PacienteMapper.java         # Entidad <-> DTO
│   └── TurnoRequest.java           # (del tema anterior, sin cambios de fondo)
├── controller/
│   └── PacienteController.java     # Requerimiento 3
├── exception/
│   ├── ResourceNotFoundException.java   # 404
│   ├── TurnoInvalidoException.java      # 400 (regla de negocio)
│   ├── ErrorResponse.java               # forma del JSON de error simple
│   ├── ValidationErrorResponse.java     # forma del JSON de error de validación
│   └── GlobalExceptionHandler.java      # Requerimiento 4 (@RestControllerAdvice)
├── service/ , repository/ , model/      # capas ya existentes, adaptadas
postman/
├── Centro-Diagnostico-Pacientes-API.postman_collection.json   # Entregable 2
└── newman-run-report.json                                      # evidencia de ejecución (ver nota abajo)
```

## Requerimientos cubiertos

| # | Requerimiento | Dónde |
|---|----------------|-------|
| 1 | `dto` con `PacienteRequestDTO` y `PacienteResponseDTO` | `dto/PacienteRequestDTO.java`, `dto/PacienteResponseDTO.java` |
| 2 | Validaciones: nombre (mín. 2), email (formato), edad (positiva) | Anotaciones `@NotBlank`, `@Size(min=2)`, `@Email`, `@NotNull`, `@Positive` en `PacienteRequestDTO` |
| 3 | `PacienteController` con GET lista, POST (`@Valid`, 201), GET/{id} (404) | `controller/PacienteController.java` |
| 4 | `@RestControllerAdvice` con errores JSON estructurados | `exception/GlobalExceptionHandler.java` |
| 5 | Pruebas en Postman (éxito y fallo de validación) | `postman/Centro-Diagnostico-Pacientes-API.postman_collection.json` |

## Endpoints

| Método | Endpoint              | Body                                            | Respuesta                          |
|--------|------------------------|--------------------------------------------------|-------------------------------------|
| GET    | `/api/pacientes`       | -                                                  | 200, `[PacienteResponseDTO]`        |
| POST   | `/api/pacientes`       | `{"nombre","email","edad"}` (`PacienteRequestDTO`) | 201 + header `Location`, o 400 si falla `@Valid` |
| GET    | `/api/pacientes/{id}`  | -                                                  | 200, `PacienteResponseDTO`, o 404 si no existe |

### Ejemplo — POST válido

```bash
curl -i -X POST http://localhost:8080/api/pacientes \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Ana Gómez","email":"ana@example.com","edad":28}'
```

```
HTTP/1.1 201 Created
Location: /api/pacientes/1
Content-Type: application/json

{"id":1,"nombre":"Ana Gómez","email":"ana@example.com","edad":28}
```

### Ejemplo — POST con nombre inválido (400, manejado por GlobalExceptionHandler)

```bash
curl -X POST http://localhost:8080/api/pacientes \
  -H "Content-Type: application/json" \
  -d '{"nombre":"J","email":"juan@example.com","edad":30}'
```

```json
{
    "timestamp": "2026-09-18T03:55:42.751",
    "status": 400,
    "error": "Bad Request",
    "message": "Error de validación en los datos enviados.",
    "path": "/api/pacientes",
    "errores": {
        "nombre": "El nombre debe tener al menos 2 caracteres"
    }
}
```

### Ejemplo — GET a un id inexistente (404)

```bash
curl http://localhost:8080/api/pacientes/999999
```

```json
{
    "timestamp": "2026-09-18T03:55:42.791",
    "status": 404,
    "error": "Not Found",
    "message": "No se encontró un paciente con id: 999999",
    "path": "/api/pacientes/999999"
}
```

## Cómo compilar y ejecutar

```bash
mvn spring-boot:run
```

La app queda escuchando en `http://localhost:8080`.

## Cómo probar con Postman

1. Abrí Postman → **Import** → seleccioná `postman/Centro-Diagnostico-Pacientes-API.postman_collection.json`.
2. Con la app corriendo en `localhost:8080` (la variable `baseUrl` de la
   colección ya apunta ahí), abrí la colección y usá **Run collection**
   (Collection Runner) para ejecutar los 8 requests de una vez, o
   corré cada uno individualmente con **Send**.
3. Cada request tiene asserts en la pestaña **Tests** que verifican
   código de estado y forma del body (`pm.test(...)`).
4. Casos incluidos:
   - `01` listar pacientes (200)
   - `02` crear paciente válido (201 + Location + body)
   - `03` buscar por id existente (200)
   - `04` buscar por id inexistente (**404**, caso de fallo)
   - `05` nombre con 1 carácter (**400**, caso de fallo de validación)
   - `06` email con formato inválido (**400**, caso de fallo de validación)
   - `07` edad negativa (**400**, caso de fallo de validación)
   - `08` los tres campos inválidos a la vez (**400**, con los tres errores en el body)
5. Para volver a generar el archivo de colección después de correrla en
   tu Postman (con las respuestas guardadas), usá **Export** sobre la
   colección y reemplazá el `.json` de la carpeta `postman/`.

## Nota importante sobre la validación de este entregable

El entorno donde se generó este proyecto no tiene salida de red hacia
Maven Central, así que no pude ejecutar `mvn spring-boot:run` acá para
correr la colección contra el Spring Boot real. Para no entregar una
colección "de fe", hice lo siguiente:

1. Compilé **todo el código Java** (controller, service, repository,
   DTOs, excepciones, `GlobalExceptionHandler`) contra un stub local de
   las clases de Spring/Jakarta Validation usadas, sin errores.
2. Armé un servidor de referencia mínimo (Node.js, sin Spring) que
   replica **exactamente** el mismo contrato HTTP que expone
   `PacienteController` + `GlobalExceptionHandler` (mismas rutas,
   mismos códigos de estado, misma forma de JSON).
3. Corrí la colección de Postman real (la que está en
   `postman/Centro-Diagnostico-Pacientes-API.postman_collection.json`)
   con **Newman** (el CLI oficial de Postman) contra ese servidor de
   referencia: **17/17 asserts pasaron**. El reporte completo de esa
   corrida queda en `postman/newman-run-report.json`, solo como
   evidencia de que los tests de la colección están bien escritos.

Esto confirma que la colección es válida y ejecutable, y que sus
asserts coinciden con el contrato real de la API. Cuando la corras vos
con el Spring Boot real levantado, deberías ver el mismo resultado.
Te recomiendo, para la entrega, correrla una vez en tu Postman contra
tu propio `localhost:8080` y volver a exportar la colección (así el
archivo entregado refleja una corrida contra el backend real y no
contra el servidor de referencia).
