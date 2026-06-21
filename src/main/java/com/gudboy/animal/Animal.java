package com.gudboy.animal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.gudboy.alarma.Alarma;
import com.gudboy.animal.estado.EnTratamiento;
import com.gudboy.animal.estado.EstadoAnimal;

public abstract class Animal {

    protected final long id;
    protected String nombre;
    protected EstadoAnimal estado;
    protected final FichaMedica fichaMedica;
    protected final List<Alarma> alarmas = new ArrayList<>();

    protected Animal(long id, String nombre, FichaMedica fichaMedica, EstadoAnimal estado) {
        this.id = id;
        this.nombre = nombre;
        this.fichaMedica = fichaMedica;
        this.estado = estado;
    }

    public abstract boolean puedeSerAdoptado();

    public boolean estaEnTratamiento() {
        return estado instanceof EnTratamiento;
    }

    public void agregarAlarma(Alarma alarma) {
        alarmas.add(alarma);
    }

    public void cambiarEstado(EstadoAnimal nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public EstadoAnimal getEstado() {
        return estado;
    }

    public FichaMedica getFichaMedica() {
        return fichaMedica;
    }

    public List<Alarma> getAlarmas() {
        return Collections.unmodifiableList(alarmas);
    }
}
