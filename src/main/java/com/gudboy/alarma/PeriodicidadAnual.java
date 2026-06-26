package com.gudboy.alarma;

import java.time.LocalDate;

public class PeriodicidadAnual implements IPeriodicidad {

    @Override
    public LocalDate proximaFecha(LocalDate desde) {
        return desde.plusYears(1);
    }
}
