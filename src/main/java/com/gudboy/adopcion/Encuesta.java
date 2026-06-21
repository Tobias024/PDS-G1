package com.gudboy.adopcion;

public class Encuesta {

    private final NivelCalidad estadoAnimal;
    private final NivelCalidad limpieza;
    private final NivelCalidad ambiente;

    public Encuesta(NivelCalidad estadoAnimal, NivelCalidad limpieza, NivelCalidad ambiente) {
        this.estadoAnimal = estadoAnimal;
        this.limpieza = limpieza;
        this.ambiente = ambiente;
    }

    public NivelCalidad getEstadoAnimal() {
        return estadoAnimal;
    }

    public NivelCalidad getLimpieza() {
        return limpieza;
    }

    public NivelCalidad getAmbiente() {
        return ambiente;
    }
}
