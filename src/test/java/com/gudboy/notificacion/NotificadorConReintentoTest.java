package com.gudboy.notificacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class NotificadorConReintentoTest {

    private static class NotificadorFallido implements INotificador {
        private final int fallaHastaIntento;
        int llamadas = 0;

        NotificadorFallido(int fallaHastaIntento) {
            this.fallaHastaIntento = fallaHastaIntento;
        }

        @Override
        public void enviar(String destino, String mensaje) {
            llamadas++;
            if (llamadas <= fallaHastaIntento) {
                throw new RuntimeException("Error de envio (intento " + llamadas + ")");
            }
        }
    }

    @Test
    void enviaCorrectamenteSiElPrimerIntentoEsExitoso() {
        NotificadorFallido fallido = new NotificadorFallido(0);
        INotificador decorado = new NotificadorConReintento(fallido, 3);

        decorado.enviar("dest", "msg");

        assertEquals(1, fallido.llamadas);
    }

    @Test
    void reintetaHastaLograrElEnvio() {
        NotificadorFallido fallido = new NotificadorFallido(2);
        INotificador decorado = new NotificadorConReintento(fallido, 3);

        decorado.enviar("dest", "msg");

        assertEquals(3, fallido.llamadas);
    }

    @Test
    void lanzaExcepcionAlAgorarLosIntentos() {
        NotificadorFallido fallido = new NotificadorFallido(5);
        INotificador decorado = new NotificadorConReintento(fallido, 3);

        assertThrows(RuntimeException.class, () -> decorado.enviar("dest", "msg"));
        assertEquals(3, fallido.llamadas);
    }
}
