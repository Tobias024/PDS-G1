package com.gudboy.notificacion;

public class NotificadorEmail implements INotificador {

    @Override
    public void enviar(String destino, String mensaje) {
        System.out.println("[Email] a " + destino + ": " + mensaje);
    }
}
