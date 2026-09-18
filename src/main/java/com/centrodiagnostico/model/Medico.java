package com.centrodiagnostico.model;

/**
 * Otra subclase de Persona que también implementa Atendible, para
 * mantener la demostración de polimorfismo de los temas anteriores.
 */
public class Medico extends Persona implements Atendible {

    private String especialidad;
    private String matricula;

    public Medico(Long id, String nombre, String especialidad, String matricula) {
        super(id, nombre);
        this.especialidad = especialidad;
        this.matricula = matricula;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String descripcion() {
        return "Dr./Dra. %s - %s (Mat. %s)".formatted(getNombre(), especialidad, matricula);
    }

    @Override
    public void atender() {
        System.out.println("El médico " + getNombre() + " (" + especialidad + ") está atendiendo su próximo turno.");
    }
}
