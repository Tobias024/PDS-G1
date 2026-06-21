package com.gudboy.animal.estado;

public class EnTratamiento implements EstadoAnimal {

    @Override
    public boolean esAdoptable() {
        return false;
    }
}
