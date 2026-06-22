package com.gudboy.alarma;

import java.time.LocalDate;

// Strategy: cada periodicidad encapsula como se calcula la proxima fecha de disparo.
// Agregar una frecuencia nueva (quincenal, anual) solo requiere una clase nueva.
public interface IPeriodicidad {
    LocalDate proximaFecha(LocalDate desde);
}
