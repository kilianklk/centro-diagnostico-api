package com.centrodiagnostico.service;

import com.centrodiagnostico.exception.ResourceNotFoundException;
import com.centrodiagnostico.model.Paciente;
import com.centrodiagnostico.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Lógica de negocio de Paciente. PacienteRepository se inyecta por
 * constructor (Inyección de Dependencias), nunca se crea con "new".
 */
@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente registrar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Paciente buscarPorId(Long id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró un paciente con id: " + id));
    }

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }
}
