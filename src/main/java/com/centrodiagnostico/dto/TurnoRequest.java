package com.centrodiagnostico.dto;

/**
 * DTO de entrada para asignar un turno. idPaciente ahora es numérico
 * porque Paciente.id pasó a ser autogenerado (Long) en este tema.
 */
public record TurnoRequest(
        Long idPaciente,
        String fecha,
        String especialidad
) {
}
