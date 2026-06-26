package com.gudboy.alarma;

import java.time.LocalDateTime;

// Creator: la Alarma crea y administra sus acciones (composicion).
public abstract class AccionAlarma {

    private boolean completado;
    private String comentario;
    private LocalDateTime fechaEjecucion;

    public abstract void ejecutar();

    public abstract String getDescripcion();

    public final void realizar(String comentario) {
        ejecutar();
        completada(comentario);
    }

    public void completada(String comentario) {
        this.completado = true;
        this.comentario = comentario;
        this.fechaEjecucion = LocalDateTime.now();
    }

    public boolean isCompletado() {
        return completado;
    }

    public String getComentario() {
        return comentario;
    }

    public LocalDateTime getFechaEjecucion() {
        return fechaEjecucion;
    }
}
