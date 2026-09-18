package com.centrodiagnostico.model;

import java.time.LocalDateTime;

public class Turno {

    private final String idTurno;
    private final Paciente paciente;
    private final LocalDateTime fecha;
    private final String especialidad;
    private EstadoTurno estado;

    public Turno(String idTurno, Paciente paciente, LocalDateTime fecha, String especialidad) {
        this.idTurno = idTurno;
        this.paciente = paciente;
        this.fecha = fecha;
        this.especialidad = especialidad;
        this.estado = EstadoTurno.PENDIENTE;
    }

    public String getIdTurno() {
        return idTurno;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public EstadoTurno getEstado() {
        return estado;
    }

    public void setEstado(EstadoTurno estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Turno[%s] %s - Paciente: %s - Especialidad: %s - Estado: %s".formatted(
                idTurno, fecha, paciente.getNombre(), especialidad, estado);
    }
}
