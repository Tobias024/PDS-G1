package com.gudboy.animal.estado;

public class Recuperado implements EstadoAnimal {

    @Override
    public boolean esAdoptable() {
        return true;
    }
}
