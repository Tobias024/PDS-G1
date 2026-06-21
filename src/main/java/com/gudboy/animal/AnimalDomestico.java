package com.gudboy.animal;

import com.gudboy.animal.estado.EstadoAnimal;

public class AnimalDomestico extends Animal {

    private final TipoDomestico tipo;
    private boolean enAdopcion;

    public AnimalDomestico(long id, String nombre, FichaMedica fichaMedica, EstadoAnimal estado, TipoDomestico tipo) {
        super(id, nombre, fichaMedica, estado);
        this.tipo = tipo;
    }

    // Depende del estado: un animal en tratamiento no puede ser adoptado hasta finalizarlo.
    @Override
    public boolean puedeSerAdoptado() {
        return estado.esAdoptable();
    }

    public TipoDomestico getTipo() {
        return tipo;
    }

    public boolean isEnAdopcion() {
        return enAdopcion;
    }

    public void setEnAdopcion(boolean enAdopcion) {
        this.enAdopcion = enAdopcion;
    }
}
