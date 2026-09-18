package com.centrodiagnostico.exception;

import java.time.LocalDateTime;

/**
 * Cuerpo JSON estándar para errores "simples" (404, 400 de negocio, 500).
 */
public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path
) {
}
