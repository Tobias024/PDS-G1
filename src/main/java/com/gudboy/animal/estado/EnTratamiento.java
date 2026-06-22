package com.gudboy.animal.estado;

import com.gudboy.animal.Animal;

public class EnTratamiento implements EstadoAnimal {

    @Override
    public boolean esAdoptable() {
        return false;
    }

    // Al finalizar el tratamiento el animal pasa a estar recuperado y vuelve a ser adoptable.
    @Override
    public void finalizarTratamiento(Animal animal) {
        animal.cambiarEstado(new Recuperado());
    }
}
