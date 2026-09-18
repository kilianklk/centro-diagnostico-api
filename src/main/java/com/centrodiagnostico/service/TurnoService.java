package com.centrodiagnostico.service;

import com.centrodiagnostico.exception.TurnoInvalidoException;
import com.centrodiagnostico.model.EstadoTurno;
import com.centrodiagnostico.model.Paciente;
import com.centrodiagnostico.model.Turno;
import com.centrodiagnostico.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Lógica de negocio de Turno. Las excepciones ahora son unchecked y
 * se resuelven de forma centralizada en GlobalExceptionHandler, así
 * que ya no hace falta declarar "throws" en la firma de estos métodos.
 */
@Service
public class TurnoService {

    private final TurnoRepository turnoRepository;
    private final PacienteService pacienteService;

    @Autowired
    public TurnoService(TurnoRepository turnoRepository, PacienteService pacienteService) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
    }

    public Turno asignarTurno(Long idPaciente, LocalDateTime fecha, String especialidad) {
        Paciente paciente = pacienteService.buscarPorId(idPaciente);

        if (fecha == null || fecha.isBefore(LocalDateTime.now())) {
            throw new TurnoInvalidoException(
                    "No se puede agendar un turno con fecha pasada: " + fecha);
        }

        Turno turno = new Turno(turnoRepository.siguienteId(), paciente, fecha, especialidad);
        return turnoRepository.save(turno);
    }

    public void atenderTurnosPendientes() {
        for (Turno turno : turnoRepository.findByEstado(EstadoTurno.PENDIENTE)) {
            turno.getPaciente().atender();
            turno.setEstado(EstadoTurno.ATENDIDO);
        }
    }

    public List<Turno> listarTurnos() {
        return turnoRepository.findAll();
    }
}
