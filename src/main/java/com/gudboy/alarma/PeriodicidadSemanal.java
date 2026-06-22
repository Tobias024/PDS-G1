package com.gudboy.alarma;

import java.time.LocalDate;

public class PeriodicidadSemanal implements IPeriodicidad {

    @Override
    public LocalDate proximaFecha(LocalDate desde) {
        return desde.plusWeeks(1);
    }
}
