package com.gudboy.notificacion;

public class NotificadorConLog extends NotificadorDecorator {

    public NotificadorConLog(INotificador wrapped) {
        super(wrapped);
    }

    @Override
    public void enviar(String destino, String mensaje) {
        System.out.println("[LOG] Enviando notificacion a " + mascararDestino(destino)
                + " (" + sanitizar(mensaje).length() + " chars)");
        wrapped.enviar(destino, mensaje);
        System.out.println("[LOG] Enviado OK");
    }

    private static String mascararDestino(String destino) {
        if (destino == null || destino.length() < 3) return "***";
        return sanitizar(destino.substring(0, 3)) + "***";
    }

    private static String sanitizar(String valor) {
        if (valor == null) return "";
        return valor.replace("\n", "").replace("\r", "").replace("\t", "");
    }
}
