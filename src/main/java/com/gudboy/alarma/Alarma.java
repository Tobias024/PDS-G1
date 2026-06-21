package com.gudboy.alarma;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.gudboy.animal.Animal;

public class Alarma {

    private final long id;
    private Periodicidad periodicidad;
    private final Animal animal;
    private final List<IVeterinarioObserver> observers = new ArrayList<>();
    private final List<AccionAlarma> acciones = new ArrayList<>();

    public Alarma(long id, Periodicidad periodicidad, Animal animal) {
        this.id = id;
        this.periodicidad = periodicidad;
        this.animal = animal;
    }

    public void disparar() {
        for (IVeterinarioObserver observer : observers) {
            observer.notificar(this);
        }
    }

    public void subscribir(IVeterinarioObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void deSubscribir(IVeterinarioObserver observer) {
        observers.remove(observer);
    }

    public void agregarAccion(AccionAlarma accion) {
        acciones.add(accion);
    }

    public long getId() {
        return id;
    }

    public Periodicidad getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(Periodicidad periodicidad) {
        this.periodicidad = periodicidad;
    }

    public Animal getAnimal() {
        return animal;
    }

    public List<AccionAlarma> getAcciones() {
        return Collections.unmodifiableList(acciones);
    }

    public int cantidadObservadores() {
        return observers.size();
    }
}
