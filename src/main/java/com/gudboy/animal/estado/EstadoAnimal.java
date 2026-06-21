package com.gudboy.animal.estado;

// State: cada estado sabe si el animal es adoptable en esa situacion.
public interface EstadoAnimal {
    boolean esAdoptable();
}
