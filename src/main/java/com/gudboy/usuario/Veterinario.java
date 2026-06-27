package com.gudboy.usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.gudboy.alarma.AccionAlarma;
import com.gudboy.alarma.Alarma;
import com.gudboy.alarma.IVeterinarioObserver;
import com.gudboy.animal.RegistroAtencion;
import com.gudboy.animal.TipoRegistro;

public class Veterinario extends Usuario implements IVeterinarioObserver {

    private String email;
    private final List<Alarma> alertasPendientes = new ArrayList<>();

    public Veterinario(String id, String nombre, String email) {
        super(id, nombre);
        this.email = email;
    }

    // Recibe la push notification cuando se dispara una alarma a la que esta suscripto.
    @Override
    public void notificar(Alarma alarma) {
        alertasPendientes.add(alarma);
        System.out.println("Push para " + nombre + ": alarma " + alarma.getId()
                + " del animal " + alarma.getAnimal().getNombre());
    }

    // Cualquier veterinario puede atender una alarma disparada.
    public RegistroAtencion atenderAlarma(Alarma alarma, String comentario, boolean esTratamiento, boolean finalizado,
                                          TipoRegistro tipoRegistro) {
        for (AccionAlarma accion : alarma.getAcciones()) {
            accion.realizar(comentario);
        }

        RegistroAtencion registro = new RegistroAtencion(
                LocalDateTime.now(),
                this,
                new ArrayList<>(alarma.getAcciones()),
                esTratamiento,
                finalizado,
                tipoRegistro);

        alarma.getAnimal().getFichaMedica().agregarRegistro(registro);
        alertasPendientes.remove(alarma);
        return registro;
    }

    public List<Alarma> getAlertasPendientes() {
        return Collections.unmodifiableList(alertasPendientes);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
