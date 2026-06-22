package com.gudboy.animal.estado;

import com.gudboy.animal.Animal;

// State: cada estado sabe si el animal es adoptable y como transiciona ante cada evento.
// Las transiciones viven dentro del estado (no en condicionales del Animal): cada estado
// concreto sobreescribe solo las transiciones validas; el resto queda prohibido por defecto.
public interface EstadoAnimal {

    boolean esAdoptable();

    default void iniciarTratamiento(Animal animal) {
        throw new IllegalStateException(
                "No se puede iniciar un tratamiento desde el estado " + getClass().getSimpleName());
    }

    default void finalizarTratamiento(Animal animal) {
        throw new IllegalStateException(
                "No se puede finalizar un tratamiento desde el estado " + getClass().getSimpleName());
    }
}
