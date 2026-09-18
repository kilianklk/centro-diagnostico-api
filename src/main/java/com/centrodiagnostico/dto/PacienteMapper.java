package com.centrodiagnostico.dto;

import com.centrodiagnostico.model.Paciente;

/**
 * Convierte entre la entidad de dominio Paciente y sus DTOs.
 * Mantener este mapeo fuera del controller y del service evita
 * repetir la lógica de conversión y deja cada capa enfocada en su
 * responsabilidad (controller: HTTP: service: negocio; mapper: forma).
 */
public final class PacienteMapper {

    private PacienteMapper() {
        // clase de utilidades, no instanciable
    }

    public static Paciente toEntity(PacienteRequestDTO dto) {
        return new Paciente(null, dto.nombre(), dto.email(), dto.edad());
    }

    public static PacienteResponseDTO toResponseDTO(Paciente paciente) {
        return new PacienteResponseDTO(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getEmail(),
                paciente.getEdad()
        );
    }
}
