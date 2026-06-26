package com.gudboy.animal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.gudboy.adopcion.Visita;
import com.gudboy.exportacion.IExportadorStrategy;

public class FichaMedica {

    private final long id;
    private double altura;
    private double peso;
    private int edad;
    private String condicionMedica;
    private final List<RegistroAtencion> registros = new ArrayList<>();
    private final List<Visita> visitas = new ArrayList<>();
    private IExportadorStrategy exportador;

    public FichaMedica(long id, double altura, double peso, int edad, String condicionMedica) {
        this.id = id;
        this.altura = altura;
        this.peso = peso;
        this.edad = edad;
        this.condicionMedica = condicionMedica;
    }

    public void agregarRegistro(RegistroAtencion registro) {
        registros.add(registro);
    }

    public void agregarVisita(Visita visita) {
        visitas.add(visita);
    }

    public List<Visita> getVisitas() {
        return Collections.unmodifiableList(visitas);
    }

    public void setExportador(IExportadorStrategy exportador) {
        this.exportador = exportador;
    }

    public void exportar() {
        if (exportador == null) {
            throw new IllegalStateException("No hay un exportador configurado para la ficha medica");
        }
        exportador.exportar(this);
    }

    public long getId() {
        return id;
    }

    public double getAltura() {
        return altura;
    }

    public double getPeso() {
        return peso;
    }

    public int getEdad() {
        return edad;
    }

    public String getCondicionMedica() {
        return condicionMedica;
    }

    public void setCondicionMedica(String condicionMedica) {
        this.condicionMedica = condicionMedica;
    }

    public List<RegistroAtencion> getRegistros() {
        return Collections.unmodifiableList(registros);
    }
}
