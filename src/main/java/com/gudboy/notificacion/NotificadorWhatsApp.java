package com.gudboy.notificacion;

public class NotificadorWhatsApp implements INotificador {

    @Override
    public void enviar(String destino, String mensaje) {
        System.out.println("[WhatsApp] a " + destino + ": " + mensaje);
    }
}
