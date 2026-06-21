package com.gudboy.adopcion;

import java.time.LocalDate;

import com.gudboy.animal.AnimalDomestico;

public class Adopcion {

    private final LocalDate fecha;
    private final String motivo;
    private final AnimalDomestico animal;
    private Seguimiento seguimiento;

    public Adopcion(LocalDate fecha, String motivo, AnimalDomestico animal) {
        this.fecha = fecha;
        this.motivo = motivo;
        this.animal = animal;
    }

    public void asignarSeguimiento(Seguimiento seguimiento) {
        this.seguimiento = seguimiento;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public AnimalDomestico getAnimal() {
        return animal;
    }

    public Seguimiento getSeguimiento() {
        return seguimiento;
    }
}
