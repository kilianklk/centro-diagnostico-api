package com.centrodiagnostico.controller;

import com.centrodiagnostico.dto.TurnoRequest;
import com.centrodiagnostico.model.Turno;
import com.centrodiagnostico.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Expone la gestión de turnos como API REST.
 * Reemplaza el uso directo de GestorTurnos del Tema 2: ahora
 * TurnoService se inyecta por constructor en vez de instanciarse
 * manualmente.
 */
@RestController
@RequestMapping("/api/turnos")
public class TurnoController {

    private final TurnoService turnoService;

    @Autowired
    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping
    public ResponseEntity<Turno> asignar(@RequestBody TurnoRequest request) {
        LocalDateTime fecha = LocalDateTime.parse(request.fecha());
        Turno turno = turnoService.asignarTurno(request.idPaciente(), fecha, request.especialidad());
        return ResponseEntity.status(HttpStatus.CREATED).body(turno);
    }

    @GetMapping
    public List<Turno> listar() {
        return turnoService.listarTurnos();
    }

    @PostMapping("/atender-pendientes")
    public ResponseEntity<String> atenderPendientes() {
        turnoService.atenderTurnosPendientes();
        return ResponseEntity.ok("Turnos pendientes atendidos correctamente.");
    }
}
