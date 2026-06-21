package com.gudboy.notificacion;

// Strategy: medio de envio del recordatorio (SMS, WhatsApp o Email).
public interface INotificador {
    void enviar(String destino, String mensaje);
}
