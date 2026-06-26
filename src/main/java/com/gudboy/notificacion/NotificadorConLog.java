package com.gudboy.notificacion;

public class NotificadorConLog extends NotificadorDecorator {

    public NotificadorConLog(INotificador wrapped) {
        super(wrapped);
    }

    @Override
    public void enviar(String destino, String mensaje) {
        System.out.println("[LOG] Enviando a " + destino + ": " + mensaje);
        wrapped.enviar(destino, mensaje);
        System.out.println("[LOG] Enviado OK");
    }
}
