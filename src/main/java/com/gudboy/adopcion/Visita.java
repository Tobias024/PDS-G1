package com.gudboy.adopcion;

import java.time.LocalDate;

public class Visita {

    private final LocalDate fecha;
    private final Encuesta encuesta;
    private final boolean continuarVisitas;

    public Visita(LocalDate fecha, Encuesta encuesta, boolean continuarVisitas) {
        this.fecha = fecha;
        this.encuesta = encuesta;
        this.continuarVisitas = continuarVisitas;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Encuesta getEncuesta() {
        return encuesta;
    }

    public boolean isContinuarVisitas() {
        return continuarVisitas;
    }
}
