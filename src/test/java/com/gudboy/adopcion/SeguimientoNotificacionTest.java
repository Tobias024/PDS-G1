package com.gudboy.adopcion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.gudboy.notificacion.INotificador;
import com.gudboy.usuario.Visitador;

class SeguimientoNotificacionTest {

    // Doble de prueba que registra las invocaciones a la estrategia de notificacion.
    private static class NotificadorEspia implements INotificador {
        private int llamadas;
        private String ultimoDestino;
        private String ultimoMensaje;

        @Override
        public void enviar(String destino, String mensaje) {
            this.llamadas++;
            this.ultimoDestino = destino;
            this.ultimoMensaje = mensaje;
        }
    }

    private Seguimiento nuevoSeguimiento(INotificador notificador) {
        Visitador visitador = new Visitador("u1", "Ana");
        Cadencia cadencia = new Cadencia("Sabado", "10:00-12:00");
        return new Seguimiento(visitador, cadencia, 3, notificador);
    }

    @Test
    void programarRecordatorioUsaLaEstrategiaConfigurada() {
        NotificadorEspia espia = new NotificadorEspia();
        Seguimiento seguimiento = nuevoSeguimiento(espia);

        seguimiento.programarRecordatorio("cliente@mail.com");

        assertEquals(1, espia.llamadas);
        assertEquals("cliente@mail.com", espia.ultimoDestino);
        assertTrue(espia.ultimoMensaje.contains("3 dias"));
        assertTrue(espia.ultimoMensaje.contains("Sabado"));
    }

    @Test
    void cambiarLaEstrategiaDeNotificacionEnTiempoDeEjecucion() {
        NotificadorEspia primero = new NotificadorEspia();
        NotificadorEspia segundo = new NotificadorEspia();
        Seguimiento seguimiento = nuevoSeguimiento(primero);

        seguimiento.programarRecordatorio("cliente@mail.com");
        seguimiento.setNotificador(segundo);
        seguimiento.programarRecordatorio("cliente@mail.com");

        assertEquals(1, primero.llamadas);
        assertEquals(1, segundo.llamadas);
    }

    @Test
    void registrarVisitaGuardaLaVisitaEnElSeguimiento() {
        Seguimiento seguimiento = nuevoSeguimiento(new NotificadorEspia());
        Encuesta encuesta = new Encuesta(NivelCalidad.BUENO, NivelCalidad.BUENO, NivelCalidad.REGULAR);
        Visita visita = new Visita(LocalDate.now(), encuesta, true);

        seguimiento.registrarVisita(visita);

        assertEquals(1, seguimiento.getVisitas().size());
        assertTrue(seguimiento.getVisitas().contains(visita));
    }
}
