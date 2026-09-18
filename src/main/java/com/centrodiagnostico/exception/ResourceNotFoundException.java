package com.centrodiagnostico.exception;

/**
 * Excepción de negocio (unchecked) que se lanza cuando se solicita
 * un recurso que no existe (por ejemplo, un paciente por id).
 *
 * Requerimiento 3: se usa en PacienteController/PacienteService para
 * que el GET /api/pacientes/{id} devuelva 404 Not Found cuando el
 * paciente no existe.
 *
 * Es una RuntimeException (unchecked) a propósito: así no ensucia las
 * firmas de los métodos de servicio/controller con "throws", y su
 * traducción a una respuesta HTTP estructurada queda centralizada en
 * GlobalExceptionHandler (Requerimiento 4), en vez de resolverse con
 * @ResponseStatus en la propia clase.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }
}
