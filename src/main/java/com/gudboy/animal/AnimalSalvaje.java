package com.gudboy.animal;

import com.gudboy.animal.estado.EstadoAnimal;

public class AnimalSalvaje extends Animal {

    private final TipoSalvaje tipo;

    public AnimalSalvaje(long id, String nombre, FichaMedica fichaMedica, EstadoAnimal estado, TipoSalvaje tipo) {
        super(id, nombre, fichaMedica, estado);
        this.tipo = tipo;
    }

    // Los animales salvajes nunca pueden ser adoptados.
    @Override
    public boolean puedeSerAdoptado() {
        return false;
    }

    public TipoSalvaje getTipo() {
        return tipo;
    }
}
