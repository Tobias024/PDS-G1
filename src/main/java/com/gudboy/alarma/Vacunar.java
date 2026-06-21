package com.gudboy.alarma;

public class Vacunar extends AccionAlarma {

    @Override
    public void ejecutar() {
        System.out.println("Ejecutando colocacion de vacuna");
    }

    @Override
    public String getDescripcion() {
        return "Colocar vacuna";
    }
}
