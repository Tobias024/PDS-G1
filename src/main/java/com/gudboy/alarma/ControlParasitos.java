package com.gudboy.alarma;

public class ControlParasitos extends AccionAlarma {

    @Override
    public void ejecutar() {
        System.out.println("Ejecutando control de parasitos");
    }

    @Override
    public String getDescripcion() {
        return "Control de parasitos";
    }
}
