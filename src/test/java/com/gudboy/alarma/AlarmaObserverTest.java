package com.gudboy.alarma;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.gudboy.animal.AnimalDomestico;
import com.gudboy.animal.FichaMedica;
import com.gudboy.animal.RegistroAtencion;
import com.gudboy.animal.TipoDomestico;
import com.gudboy.animal.estado.EnTratamiento;
import com.gudboy.usuario.Veterinario;

class AlarmaObserverTest {

    private AnimalDomestico animal;
    private Alarma alarma;

    @BeforeEach
    void setUp() {
        FichaMedica ficha = new FichaMedica(1L, 0.5, 10.0, 4, "Control periodico");
        animal = new AnimalDomestico(1L, "Firulais", ficha, new EnTratamiento(), TipoDomestico.PERRO);
        alarma = new Alarma(1L, new PeriodicidadMensual(), animal);
        alarma.agregarAccion(new ControlParasitos());
        alarma.agregarAccion(new Vacunar());
    }

    @Test
    void alDispararSeNotificaATodosLosVeterinariosSuscriptos() {
        Veterinario uno = new Veterinario("v1", "Dr. House", "house@gudboy.com");
        Veterinario dos = new Veterinario("v2", "Dra. Grey", "grey@gudboy.com");
        alarma.subscribir(uno);
        alarma.subscribir(dos);

        alarma.disparar();

        assertTrue(uno.getAlertasPendientes().contains(alarma));
        assertTrue(dos.getAlertasPendientes().contains(alarma));
    }

    @Test
    void unVeterinarioDesuscriptoNoRecibeLaNotificacion() {
        Veterinario uno = new Veterinario("v1", "Dr. House", "house@gudboy.com");
        Veterinario dos = new Veterinario("v2", "Dra. Grey", "grey@gudboy.com");
        alarma.subscribir(uno);
        alarma.subscribir(dos);
        alarma.deSubscribir(dos);

        alarma.disparar();

        assertTrue(uno.getAlertasPendientes().contains(alarma));
        assertFalse(dos.getAlertasPendientes().contains(alarma));
    }

    @Test
    void aplicarAntiparasitoEsUnaAccionValida() {
        AplicarAntiparasito accion = new AplicarAntiparasito();

        accion.realizar("Antiparasitario aplicado");

        assertTrue(accion.isCompletado());
        assertEquals("Antiparasitario aplicado", accion.getComentario());
    }

    @Test
    void realizarEncapsulaEjecutarYCompletar() {
        ControlParasitos accion = new ControlParasitos();

        accion.realizar("Control aplicado");

        assertTrue(accion.isCompletado());
        assertEquals("Control aplicado", accion.getComentario());
    }

    @Test
    void atenderAlarmaCompletaLasAccionesYRegistraEnLaFichaMedica() {
        Veterinario veterinario = new Veterinario("v1", "Dr. House", "house@gudboy.com");
        alarma.subscribir(veterinario);
        alarma.disparar();

        RegistroAtencion registro = veterinario.atenderAlarma(alarma, "Tratamiento aplicado", true, false);

        for (AccionAlarma accion : alarma.getAcciones()) {
            assertTrue(accion.isCompletado());
            assertEquals("Tratamiento aplicado", accion.getComentario());
        }
        assertEquals(1, animal.getFichaMedica().getRegistros().size());
        assertEquals(veterinario, registro.getVeterinario());
        assertTrue(registro.esTratamiento());
        assertFalse(registro.isFinalizado());
        assertFalse(veterinario.getAlertasPendientes().contains(alarma));
    }
}
