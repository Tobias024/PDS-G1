package com.gudboy.notificacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class NotificadorConPrefijoTest {

    private static class NotificadorEspia implements INotificador {
        final List<String> destinosRecibidos = new ArrayList<>();
        final List<String> mensajesRecibidos = new ArrayList<>();

        @Override
        public void enviar(String destino, String mensaje) {
            destinosRecibidos.add(destino);
            mensajesRecibidos.add(mensaje);
        }
    }

    @Test
    void agregarPrefijoAlMensaje() {
        NotificadorEspia espia = new NotificadorEspia();
        INotificador decorado = new NotificadorConPrefijo(espia, "[GUD BOY]");

        decorado.enviar("cliente@mail.com", "Recordatorio de visita");

        assertEquals("[GUD BOY] Recordatorio de visita", espia.mensajesRecibidos.get(0));
    }

    @Test
    void elDestinoNoSeModifica() {
        NotificadorEspia espia = new NotificadorEspia();
        INotificador decorado = new NotificadorConPrefijo(espia, "[GUD BOY]");

        decorado.enviar("cliente@mail.com", "Recordatorio");

        assertEquals("cliente@mail.com", espia.destinosRecibidos.get(0));
    }

    @Test
    void prefijoVacioNoAlteraElMensaje() {
        NotificadorEspia espia = new NotificadorEspia();
        INotificador decorado = new NotificadorConPrefijo(espia, "");

        decorado.enviar("dest", "Hola");

        assertEquals("Hola", espia.mensajesRecibidos.get(0));
    }

    @Test
    void esApilableConOtrosDecoradores() {
        NotificadorEspia espia = new NotificadorEspia();
        INotificador decorado = new NotificadorConPrefijo(new NotificadorConLog(espia), "[GUD BOY]");

        decorado.enviar("dest", "Mensaje");

        assertTrue(espia.mensajesRecibidos.get(0).startsWith("[GUD BOY]"));
    }
}
