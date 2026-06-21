package com.gudboy.usuario;

import java.time.LocalDate;

import com.gudboy.adopcion.Encuesta;
import com.gudboy.adopcion.Visita;

public class Visitador extends Usuario {

    public Visitador(String id, String nombre) {
        super(id, nombre);
    }

    public Visita realizarVisita(Encuesta encuesta, boolean continuarVisitas) {
        return new Visita(LocalDate.now(), encuesta, continuarVisitas);
    }
}
