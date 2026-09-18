package com.centrodiagnostico.dto;

/**
 * DTO de salida para representar un paciente en las respuestas de la API.
 * Separarlo del DTO de entrada evita, por ejemplo, que el cliente pueda
 * mandar un "id" en el POST: el id siempre lo asigna el servidor.
 */
public record PacienteResponseDTO(
        Long id,
        String nombre,
        String email,
        Integer edad
) {
}
