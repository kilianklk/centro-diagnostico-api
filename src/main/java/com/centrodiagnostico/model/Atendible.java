package com.centrodiagnostico.model;

/**
 * Contrato para toda entidad que puede ser "atendida" dentro
 * del Centro de Diagnóstico (pacientes, médicos, etc.).
 * Sin cambios respecto al Tema 2: las interfaces de dominio
 * no dependen de Spring.
 */
public interface Atendible {

    void atender();
}
