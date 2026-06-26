package com.gudboy.adopcion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.gudboy.animal.AnimalDomestico;
import com.gudboy.animal.TipoDomestico;

public class Cliente {

    public static final int MAX_ADOPCIONES = 2;

    private String nombre;
    private String apellido;
    private String estadoCivil;
    private String email;
    private String telefono;
    private Ocupacion ocupacion;
    private boolean tieneOtrasMascotas;
    private String motivoAdopcion;
    private final List<Adopcion> adopciones = new ArrayList<>();
    private final List<TipoDomestico> tiposInteresados = new ArrayList<>();

    public Cliente(String nombre, String apellido, String estadoCivil, String email, String telefono,
                   Ocupacion ocupacion, boolean tieneOtrasMascotas, String motivoAdopcion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.estadoCivil = estadoCivil;
        this.email = email;
        this.telefono = telefono;
        this.ocupacion = ocupacion;
        this.tieneOtrasMascotas = tieneOtrasMascotas;
        this.motivoAdopcion = motivoAdopcion;
    }

    // Cada cliente puede adoptar un maximo de 2 animales domesticos.
    public boolean puedeAdoptar() {
        return adopciones.size() < MAX_ADOPCIONES;
    }

    public Adopcion adoptar(AnimalDomestico animal, String motivo) {
        if (!puedeAdoptar()) {
            throw new IllegalStateException("El cliente alcanzo el maximo de adopciones permitidas");
        }
        if (!animal.esAdoptable()) {
            throw new IllegalStateException("El animal no se encuentra disponible para adopcion");
        }
        Adopcion adopcion = new Adopcion(LocalDate.now(), motivo, animal);
        animal.setEnAdopcion(false);
        adopciones.add(adopcion);
        return adopcion;
    }

    public void agregarTipoInteresado(TipoDomestico tipo) {
        tiposInteresados.add(tipo);
    }

    public List<TipoDomestico> getTiposInteresados() {
        return Collections.unmodifiableList(tiposInteresados);
    }

    public List<Adopcion> getAdopciones() {
        return Collections.unmodifiableList(adopciones);
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public Ocupacion getOcupacion() {
        return ocupacion;
    }

    public boolean isTieneOtrasMascotas() {
        return tieneOtrasMascotas;
    }

    public String getMotivoAdopcion() {
        return motivoAdopcion;
    }
}
