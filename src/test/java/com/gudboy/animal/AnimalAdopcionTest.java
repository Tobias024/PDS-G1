package com.gudboy.animal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.gudboy.animal.estado.EnTratamiento;
import com.gudboy.animal.estado.Recuperado;
import com.gudboy.animal.estado.Sano;

class AnimalAdopcionTest {

    private FichaMedica nuevaFicha() {
        return new FichaMedica(1L, 0.4, 8.0, 3, "Sin observaciones");
    }

    @Test
    void domesticoSanoPuedeSerAdoptado() {
        AnimalDomestico perro = new AnimalDomestico(1L, "Firulais", nuevaFicha(), new Sano(), TipoDomestico.PERRO);

        assertTrue(perro.puedeSerAdoptado());
        assertFalse(perro.estaEnTratamiento());
    }

    @Test
    void domesticoEnTratamientoNoPuedeSerAdoptado() {
        AnimalDomestico gato = new AnimalDomestico(2L, "Michi", nuevaFicha(), new EnTratamiento(), TipoDomestico.GATO);

        assertFalse(gato.puedeSerAdoptado());
        assertTrue(gato.estaEnTratamiento());
    }

    @Test
    void domesticoRecuperadoVuelveAEstarDisponible() {
        AnimalDomestico gato = new AnimalDomestico(2L, "Michi", nuevaFicha(), new EnTratamiento(), TipoDomestico.GATO);
        assertFalse(gato.puedeSerAdoptado());

        gato.cambiarEstado(new Recuperado());

        assertTrue(gato.puedeSerAdoptado());
        assertFalse(gato.estaEnTratamiento());
    }

    @Test
    void salvajeNuncaPuedeSerAdoptado() {
        AnimalSalvaje zorro = new AnimalSalvaje(3L, "Zorrito", nuevaFicha(), new Sano(), TipoSalvaje.ZORRO);

        assertFalse(zorro.puedeSerAdoptado());
    }
}
