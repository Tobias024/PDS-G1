package com.gudboy.notificacion;

public class NotificadorConPrefijo extends NotificadorDecorator {

    private final String prefijo;

    public NotificadorConPrefijo(INotificador wrapped, String prefijo) {
        super(wrapped);
        this.prefijo = prefijo;
    }

    @Override
    public void enviar(String destino, String mensaje) {
        String mensajeConPrefijo = prefijo.isEmpty() ? mensaje : prefijo + " " + mensaje;
        wrapped.enviar(destino, mensajeConPrefijo);
    }
}
