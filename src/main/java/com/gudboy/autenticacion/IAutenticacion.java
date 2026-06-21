package com.gudboy.autenticacion;

import com.gudboy.usuario.Usuario;

// Interfaz que espera nuestro sistema para resolver usuarios.
public interface IAutenticacion {
    Usuario obtenerUsuario(String id);
}
