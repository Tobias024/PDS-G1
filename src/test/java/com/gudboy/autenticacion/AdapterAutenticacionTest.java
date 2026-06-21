package com.gudboy.autenticacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.gudboy.usuario.Usuario;
import com.gudboy.usuario.Veterinario;
import com.gudboy.usuario.Visitador;

class AdapterAutenticacionTest {

    private AuthServiceExterno servicioExterno;
    private AdaptadorAutenticacion adaptador;

    @BeforeEach
    void setUp() {
        servicioExterno = new AuthServiceExterno();
        adaptador = new AdaptadorAutenticacion(servicioExterno);
    }

    @Test
    void adaptaUnVeterinarioDelServicioExterno() {
        servicioExterno.registrar(new UserDTO("v1", "Dr. House", "house@gudboy.com", "VETERINARIO"));

        Usuario usuario = adaptador.obtenerUsuario("v1");

        Veterinario veterinario = assertInstanceOf(Veterinario.class, usuario);
        assertEquals("v1", veterinario.getId());
        assertEquals("Dr. House", veterinario.getNombre());
        assertEquals("house@gudboy.com", veterinario.getEmail());
    }

    @Test
    void adaptaUnVisitadorDelServicioExterno() {
        servicioExterno.registrar(new UserDTO("u1", "Ana", "ana@gudboy.com", "VISITADOR"));

        Usuario usuario = adaptador.obtenerUsuario("u1");

        assertInstanceOf(Visitador.class, usuario);
        assertEquals("Ana", usuario.getNombre());
    }

    @Test
    void rolDesconocidoLanzaExcepcion() {
        servicioExterno.registrar(new UserDTO("x1", "Otro", "otro@gudboy.com", "ADMIN"));

        assertThrows(IllegalArgumentException.class, () -> adaptador.obtenerUsuario("x1"));
    }

    @Test
    void usuarioInexistenteLanzaExcepcion() {
        assertThrows(NoSuchElementException.class, () -> adaptador.obtenerUsuario("desconocido"));
    }
}
