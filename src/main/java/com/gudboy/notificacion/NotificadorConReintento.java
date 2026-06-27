package com.gudboy.notificacion;

public class NotificadorConReintento extends NotificadorDecorator {

    private final int intentos;

    public NotificadorConReintento(INotificador wrapped, int intentos) {
        super(wrapped);
        this.intentos = intentos;
    }

    @Override
    public void enviar(String destino, String mensaje) {
        RuntimeException ultimoError = null;
        for (int i = 0; i < intentos; i++) {
            try {
                wrapped.enviar(destino, mensaje);
                return;
            } catch (RuntimeException e) {
                ultimoError = e;
            }
        }
        throw ultimoError;
    }
}
