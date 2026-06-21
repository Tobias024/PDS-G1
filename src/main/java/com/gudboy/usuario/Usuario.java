package com.gudboy.usuario;

public abstract class Usuario {

    protected final String id;
    protected String nombre;

    protected Usuario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
