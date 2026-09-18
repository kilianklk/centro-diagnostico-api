package com.centrodiagnostico.exception;

/**
 * Excepción de negocio (unchecked) que se lanza al intentar agendar
 * un turno con una fecha pasada. Se maneja de forma centralizada en
 * GlobalExceptionHandler, igual que ResourceNotFoundException.
 */
public class TurnoInvalidoException extends RuntimeException {

    public TurnoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
