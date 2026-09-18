package com.centrodiagnostico.exception;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Cuerpo JSON para errores de validación de @Valid (400): además de
 * los campos estándar, incluye un mapa campo -> mensaje con el detalle
 * de qué falló en cada atributo del DTO (Requerimiento 4).
 */
public record ValidationErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path,
        Map<String, String> errores
) {
}
