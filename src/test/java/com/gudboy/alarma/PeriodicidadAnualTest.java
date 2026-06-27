package com.gudboy.alarma;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class PeriodicidadAnualTest {

    @Test
    void proximaFechaEsUnAnioMasTarde() {
        PeriodicidadAnual periodicidad = new PeriodicidadAnual();
        LocalDate hoy = LocalDate.of(2025, 6, 25);

        LocalDate proxima = periodicidad.proximaFecha(hoy);

        assertEquals(LocalDate.of(2026, 6, 25), proxima);
    }
}
