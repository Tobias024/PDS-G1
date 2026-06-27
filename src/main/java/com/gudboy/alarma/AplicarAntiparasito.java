package com.gudboy.alarma;

public class AplicarAntiparasito extends AccionAlarma {

    @Override
    public void ejecutar() {
        System.out.println("Aplicando antiparasitario");
    }

    @Override
    public String getDescripcion() {
        return "Aplicacion de antiparasitario";
    }
}
