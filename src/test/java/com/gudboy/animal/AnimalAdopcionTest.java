package com.gudboy.animal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

        assertTrue(perro.esAdoptable());
        assertFalse(perro.estaEnTratamiento());
    }

    @Test
    void domesticoEnTratamientoNoPuedeSerAdoptado() {
        AnimalDomestico gato = new AnimalDomestico(2L, "Michi", nuevaFicha(), new EnTratamiento(), TipoDomestico.GATO);

        assertFalse(gato.esAdoptable());
        assertTrue(gato.estaEnTratamiento());
    }

    @Test
    void domesticoRecuperadoVuelveAEstarDisponible() {
        AnimalDomestico gato = new AnimalDomestico(2L, "Michi", nuevaFicha(), new EnTratamiento(), TipoDomestico.GATO);
        assertFalse(gato.esAdoptable());

        // La transicion vive dentro del estado (State): EnTratamiento -> Recuperado.
        gato.finalizarTratamiento();

        assertTrue(gato.getEstado() instanceof Recuperado);
        assertTrue(gato.esAdoptable());
        assertFalse(gato.estaEnTratamiento());
    }

    @Test
    void noSePuedeFinalizarTratamientoDeUnAnimalSano() {
        AnimalDomestico perro = new AnimalDomestico(5L, "Toby", nuevaFicha(), new Sano(), TipoDomestico.PERRO);

        assertThrows(IllegalStateException.class, perro::finalizarTratamiento);
    }

    @Test
    void salvajeNuncaPuedeSerAdoptado() {
        AnimalSalvaje zorro = new AnimalSalvaje(3L, "Zorrito", nuevaFicha(), new Sano(), TipoSalvaje.ZORRO);

        assertFalse(zorro.esAdoptable());
    }
}
