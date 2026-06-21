package com.gudboy.notificacion;

public class NotificadorSMS implements INotificador {

    @Override
    public void enviar(String destino, String mensaje) {
        System.out.println("[SMS] a " + destino + ": " + mensaje);
    }
}
