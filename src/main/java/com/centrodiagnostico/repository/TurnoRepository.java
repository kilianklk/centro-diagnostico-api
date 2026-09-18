package com.centrodiagnostico.repository;

import com.centrodiagnostico.model.EstadoTurno;
import com.centrodiagnostico.model.Turno;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Capa de acceso a datos de Turno. Bean singleton administrado por Spring.
 */
@Repository
public class TurnoRepository {

    private final Map<String, Turno> turnos = new ConcurrentHashMap<>();
    private final AtomicInteger contador = new AtomicInteger(1);

    public Turno save(Turno turno) {
        turnos.put(turno.getIdTurno(), turno);
        return turno;
    }

    public String siguienteId() {
        return "T-" + contador.getAndIncrement();
    }

    public List<Turno> findAll() {
        return List.copyOf(turnos.values());
    }

    public List<Turno> findByEstado(EstadoTurno estado) {
        return turnos.values().stream()
                .filter(t -> t.getEstado() == estado)
                .collect(Collectors.toList());
    }
}
