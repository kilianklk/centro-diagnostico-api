package com.centrodiagnostico.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * DTO de entrada para crear un paciente vía POST /api/pacientes.
 *
 * Requerimiento 2 — validaciones aplicadas:
 *  - nombre: obligatorio, mínimo 2 caracteres.
 *  - email: obligatorio, con formato de correo válido.
 *  - edad: obligatoria, debe ser un número positivo.
 *
 * Al usar un record de Java 21, las anotaciones de Jakarta Bean
 * Validation se colocan directamente sobre cada componente y Spring
 * las valida igual que en una clase tradicional, siempre que el
 * parámetro del controller esté anotado con @Valid.
 */
public record PacienteRequestDTO(

        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
        String nombre,

        @NotBlank(message = "El email es obligatorio")
        @Email(message = "El email debe tener un formato válido")
        String email,

        @NotNull(message = "La edad es obligatoria")
        @Positive(message = "La edad debe ser un número positivo")
        Integer edad
) {
}
