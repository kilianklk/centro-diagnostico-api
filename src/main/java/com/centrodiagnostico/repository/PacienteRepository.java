package com.centrodiagnostico.repository;

import com.centrodiagnostico.model.Paciente;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Capa de acceso a datos de Paciente (almacenamiento en memoria).
 * Bean singleton gestionado por Spring (@Repository).
 */
@Repository
public class PacienteRepository {

    private final Map<Long, Paciente> pacientes = new ConcurrentHashMap<>();
    private final AtomicLong secuenciaId = new AtomicLong(1);

    public Paciente save(Paciente paciente) {
        if (paciente.getId() == null) {
            paciente.setId(secuenciaId.getAndIncrement());
        }
        pacientes.put(paciente.getId(), paciente);
        return paciente;
    }

    public Optional<Paciente> findById(Long id) {
        return Optional.ofNullable(pacientes.get(id));
    }

    public List<Paciente> findAll() {
        return List.copyOf(pacientes.values());
    }
}
