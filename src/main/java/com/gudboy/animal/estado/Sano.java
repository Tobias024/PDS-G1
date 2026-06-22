package com.gudboy.animal.estado;

import com.gudboy.animal.Animal;

public class Sano implements EstadoAnimal {

    @Override
    public boolean esAdoptable() {
        return true;
    }

    // Un animal sano que requiere atencion entra en tratamiento.
    @Override
    public void iniciarTratamiento(Animal animal) {
        animal.cambiarEstado(new EnTratamiento());
    }
}
