package com.gudboy.alarma;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.gudboy.animal.Animal;

public class Alarma {

    private final long id;
    private IPeriodicidad periodicidad;
    private final Animal animal;
    private final List<IVeterinarioObserver> observers = new ArrayList<>();
    private final List<AccionAlarma> acciones = new ArrayList<>();
    private LocalDate proximaEjecucion;

    public Alarma(long id, IPeriodicidad periodicidad, Animal animal) {
        this.id = id;
        this.periodicidad = periodicidad;
        this.animal = animal;
    }

    public void disparar() {
        for (IVeterinarioObserver observer : observers) {
            observer.notificar(this);
        }
        // La estrategia de periodicidad resuelve cuando vuelve a dispararse la alarma.
        proximaEjecucion = periodicidad.proximaFecha(LocalDate.now());
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

    public IPeriodicidad getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(IPeriodicidad periodicidad) {
        this.periodicidad = periodicidad;
    }

    public LocalDate getProximaEjecucion() {
        return proximaEjecucion;
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
