package com.gudboy.adopcion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.gudboy.notificacion.INotificador;
import com.gudboy.usuario.Visitador;

public class Seguimiento {

    private final Visitador visitador;
    private final Cadencia cadencia;
    private int diasAnticipacion;
    private INotificador notificador;
    private final List<Visita> visitas = new ArrayList<>();

    public Seguimiento(Visitador visitador, Cadencia cadencia, int diasAnticipacion, INotificador notificador) {
        this.visitador = visitador;
        this.cadencia = cadencia;
        this.diasAnticipacion = diasAnticipacion;
        this.notificador = notificador;
    }

    // Envia el recordatorio segun la preferencia del cliente, N dias antes de la visita.
    public void programarRecordatorio(String destino) {
        String mensaje = "Recordatorio de visita (" + cadencia.descripcion() + ") "
                + diasAnticipacion + " dias antes";
        notificador.enviar(destino, mensaje);
    }

    public void registrarVisita(Visita visita) {
        visitas.add(visita);
    }

    public void setNotificador(INotificador notificador) {
        this.notificador = notificador;
    }

    public void setDiasAnticipacion(int diasAnticipacion) {
        this.diasAnticipacion = diasAnticipacion;
    }

    public Visitador getVisitador() {
        return visitador;
    }

    public Cadencia getCadencia() {
        return cadencia;
    }

    public int getDiasAnticipacion() {
        return diasAnticipacion;
    }

    public List<Visita> getVisitas() {
        return Collections.unmodifiableList(visitas);
    }
}
