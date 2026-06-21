package com.gudboy.adopcion;

// Dia y rango horario de visita a convenir con el cliente.
public class Cadencia {

    private final String dia;
    private final String rangoHorario;

    public Cadencia(String dia, String rangoHorario) {
        this.dia = dia;
        this.rangoHorario = rangoHorario;
    }

    public String getDia() {
        return dia;
    }

    public String getRangoHorario() {
        return rangoHorario;
    }

    public String descripcion() {
        return dia + " " + rangoHorario;
    }
}
