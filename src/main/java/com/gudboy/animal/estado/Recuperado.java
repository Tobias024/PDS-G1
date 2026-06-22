package com.gudboy.animal.estado;

import com.gudboy.animal.Animal;

public class Recuperado implements EstadoAnimal {

    @Override
    public boolean esAdoptable() {
        return true;
    }

    // Un animal recuperado puede recaer y volver a tratamiento.
    @Override
    public void iniciarTratamiento(Animal animal) {
        animal.cambiarEstado(new EnTratamiento());
    }
}
