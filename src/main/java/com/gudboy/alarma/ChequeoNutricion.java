package com.gudboy.alarma;

public class ChequeoNutricion extends AccionAlarma {

    @Override
    public void ejecutar() {
        System.out.println("Ejecutando chequeo de nutricion");
    }

    @Override
    public String getDescripcion() {
        return "Chequear nutricion";
    }
}
