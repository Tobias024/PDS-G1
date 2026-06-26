package com.gudboy.animal;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import com.gudboy.alarma.AccionAlarma;
import com.gudboy.usuario.Veterinario;

public class RegistroAtencion {

    private final LocalDateTime fecha;
    private final Veterinario veterinario;
    private final List<AccionAlarma> acciones;
    private final boolean esTratamiento;
    private boolean finalizado;
    private final TipoRegistro tipoRegistro;

    public RegistroAtencion(LocalDateTime fecha, Veterinario veterinario, List<AccionAlarma> acciones,
                            boolean esTratamiento, boolean finalizado, TipoRegistro tipoRegistro) {
        this.fecha = fecha;
        this.veterinario = veterinario;
        this.acciones = acciones;
        this.esTratamiento = esTratamiento;
        this.finalizado = finalizado;
        this.tipoRegistro = tipoRegistro;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public List<AccionAlarma> getAcciones() {
        return Collections.unmodifiableList(acciones);
    }

    public boolean esTratamiento() {
        return esTratamiento;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public TipoRegistro getTipoRegistro() {
        return tipoRegistro;
    }
}
