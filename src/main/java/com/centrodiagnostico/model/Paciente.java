package com.centrodiagnostico.model;

/**
 * Entidad de dominio Paciente. El id lo asigna el repositorio al
 * guardar (por eso el constructor lo acepta como null al crear un
 * paciente nuevo desde el controller).
 */
public class Paciente extends Persona implements Atendible {

    private String email;
    private Integer edad;

    public Paciente(Long id, String nombre, String email, Integer edad) {
        super(id, nombre);
        this.email = email;
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    @Override
    public String descripcion() {
        return "Paciente [%s] %s - %s (%s años)".formatted(getId(), getNombre(), email, edad);
    }

    @Override
    public void atender() {
        System.out.println("Atendiendo al paciente " + getNombre() + " (" + email + ")");
    }
}
