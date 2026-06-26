package com.gudboy.notificacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class NotificadorDecoratorTest {

    private static class NotificadorEspia implements INotificador {
        final List<String> mensajesEnviados = new ArrayList<>();

        @Override
        public void enviar(String destino, String mensaje) {
            mensajesEnviados.add(mensaje);
        }
    }

    @Test
    void notificadorConLogDelegaAlWrapped() {
        NotificadorEspia espia = new NotificadorEspia();
        INotificador decorado = new NotificadorConLog(espia);

        decorado.enviar("cliente@mail.com", "Recordatorio");

        assertEquals(1, espia.mensajesEnviados.size());
        assertEquals("Recordatorio", espia.mensajesEnviados.get(0));
    }

    @Test
    void notificadorConLogEsUnINotificador() {
        INotificador decorado = new NotificadorConLog(new NotificadorEmail());

        assertTrue(decorado instanceof INotificador);
    }

    @Test
    void decoradoresSeApilan() {
        NotificadorEspia espia = new NotificadorEspia();
        INotificador dobleDecorado = new NotificadorConLog(new NotificadorConLog(espia));

        dobleDecorado.enviar("dest", "msg");

        assertEquals(1, espia.mensajesEnviados.size());
    }
}
