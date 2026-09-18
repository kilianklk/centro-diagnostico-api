package com.centrodiagnostico.controller;

import com.centrodiagnostico.dto.PacienteMapper;
import com.centrodiagnostico.dto.PacienteRequestDTO;
import com.centrodiagnostico.dto.PacienteResponseDTO;
import com.centrodiagnostico.model.Paciente;
import com.centrodiagnostico.service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * API REST de Pacientes.
 *
 * PacienteService se inyecta por constructor (Inyección de Dependencias,
 * heredado del tema anterior); ningún "new PacienteService()" en esta clase.
 */
@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    /**
     * GET /api/pacientes -> lista de pacientes registrados.
     */
    @GetMapping
    public List<PacienteResponseDTO> listar() {
        return pacienteService.listarTodos().stream()
                .map(PacienteMapper::toResponseDTO)
                .toList();
    }

    /**
     * POST /api/pacientes -> recibe el DTO validado con @Valid.
     * Si la validación falla, Spring lanza MethodArgumentNotValidException
     * ANTES de que este método se ejecute, y GlobalExceptionHandler la
     * convierte en un 400 con el detalle de los campos inválidos.
     * Si todo es válido, devuelve 201 Created con el paciente creado
     * y el header Location apuntando al nuevo recurso.
     */
    @PostMapping
    public ResponseEntity<PacienteResponseDTO> crear(@Valid @RequestBody PacienteRequestDTO request) {
        Paciente pacienteCreado = pacienteService.registrar(PacienteMapper.toEntity(request));
        PacienteResponseDTO body = PacienteMapper.toResponseDTO(pacienteCreado);
        URI location = URI.create("/api/pacientes/" + pacienteCreado.getId());
        return ResponseEntity.created(location).body(body);
    }

    /**
     * GET /api/pacientes/{id} -> paciente solicitado, o dispara
     * ResourceNotFoundException (404) si no existe. La excepción se
     * lanza dentro de pacienteService.buscarPorId(id) y la resuelve
     * GlobalExceptionHandler.
     */
    @GetMapping("/{id}")
    public PacienteResponseDTO obtener(@PathVariable Long id) {
        return PacienteMapper.toResponseDTO(pacienteService.buscarPorId(id));
    }
}
