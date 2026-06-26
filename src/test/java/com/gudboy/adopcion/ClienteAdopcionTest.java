package com.gudboy.adopcion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.gudboy.animal.AnimalDomestico;
import com.gudboy.animal.FichaMedica;
import com.gudboy.animal.TipoDomestico;
import com.gudboy.animal.estado.EnTratamiento;
import com.gudboy.animal.estado.Sano;

class ClienteAdopcionTest {

    private Cliente nuevoCliente() {
        return new Cliente("Juan", "Perez", "Soltero", "juan@mail.com", "1122334455",
                Ocupacion.EMPLEADO, false, "Compania");
    }

    private AnimalDomestico domesticoSano(long id) {
        FichaMedica ficha = new FichaMedica(id, 0.4, 8.0, 2, "Sano");
        return new AnimalDomestico(id, "Mascota" + id, ficha, new Sano(), TipoDomestico.PERRO);
    }

    @Test
    void clientePuedeRegistrarTiposDeAnimalesInteresados() {
        Cliente cliente = nuevoCliente();

        cliente.agregarTipoInteresado(TipoDomestico.PERRO);
        cliente.agregarTipoInteresado(TipoDomestico.GATO);

        assertEquals(2, cliente.getTiposInteresados().size());
        assertTrue(cliente.getTiposInteresados().contains(TipoDomestico.PERRO));
        assertTrue(cliente.getTiposInteresados().contains(TipoDomestico.GATO));
    }

    @Test
    void clienteNuevoPuedeAdoptar() {
        Cliente cliente = nuevoCliente();

        assertTrue(cliente.puedeAdoptar());
        assertEquals(0, cliente.getAdopciones().size());
    }

    @Test
    void adoptarRegistraLaAdopcion() {
        Cliente cliente = nuevoCliente();

        Adopcion adopcion = cliente.adoptar(domesticoSano(1L), "Quiere companuia");

        assertEquals(1, cliente.getAdopciones().size());
        assertEquals("Quiere companuia", adopcion.getMotivo());
    }

    @Test
    void clienteNoPuedeSuperarElMaximoDeDosAdopciones() {
        Cliente cliente = nuevoCliente();
        cliente.adoptar(domesticoSano(1L), "Primera");
        cliente.adoptar(domesticoSano(2L), "Segunda");

        assertFalse(cliente.puedeAdoptar());
        assertThrows(IllegalStateException.class, () -> cliente.adoptar(domesticoSano(3L), "Tercera"));
        assertEquals(2, cliente.getAdopciones().size());
    }

    @Test
    void noSePuedeAdoptarUnAnimalEnTratamiento() {
        Cliente cliente = nuevoCliente();
        FichaMedica ficha = new FichaMedica(9L, 0.4, 8.0, 2, "En tratamiento");
        AnimalDomestico enTratamiento =
                new AnimalDomestico(9L, "Rocky", ficha, new EnTratamiento(), TipoDomestico.PERRO);

        assertThrows(IllegalStateException.class, () -> cliente.adoptar(enTratamiento, "No disponible"));
        assertEquals(0, cliente.getAdopciones().size());
    }
}
