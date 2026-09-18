package com.centrodiagnostico.model;

import java.util.Objects;

/**
 * Clase abstracta de dominio. Sigue sin ser un bean de Spring: los
 * objetos de dominio se crean con "new" en tiempo de negocio (cuando
 * se registra un paciente, por ejemplo); lo gestionado por Spring son
 * los componentes de las capas repository/service/controller.
 */
public abstract class Persona {

    private Long id;
    private String nombre;

    protected Persona(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public abstract String descripcion();

    @Override
    public String toString() {
        return descripcion();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona persona)) return false;
        return Objects.equals(id, persona.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
