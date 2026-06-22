package com.gudboy.alarma;

import java.time.LocalDate;

public class PeriodicidadDiaria implements IPeriodicidad {

    @Override
    public LocalDate proximaFecha(LocalDate desde) {
        return desde.plusDays(1);
    }
}
