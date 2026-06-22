package com.gudboy.alarma;

import java.time.LocalDate;

public class PeriodicidadMensual implements IPeriodicidad {

    @Override
    public LocalDate proximaFecha(LocalDate desde) {
        return desde.plusMonths(1);
    }
}
