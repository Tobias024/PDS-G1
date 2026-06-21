package com.gudboy.animal.estado;

public class Sano implements EstadoAnimal {

    @Override
    public boolean esAdoptable() {
        return true;
    }
}
