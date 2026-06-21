package com.gudboy.alarma;

public class ChequeoMedico extends AccionAlarma {

    @Override
    public void ejecutar() {
        System.out.println("Ejecutando chequeo de peso y tamano");
    }

    @Override
    public String getDescripcion() {
        return "Comprobar peso y tamano";
    }
}
