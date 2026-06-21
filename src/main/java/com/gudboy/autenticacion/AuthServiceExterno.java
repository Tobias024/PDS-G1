package com.gudboy.autenticacion;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

// Servicio provisto por el equipo de seguridad. Su contrato no es modificable por nosotros.
public class AuthServiceExterno {

    private final Map<String, UserDTO> usuarios = new HashMap<>();

    public void registrar(UserDTO usuario) {
        usuarios.put(usuario.getUid(), usuario);
    }

    public UserDTO getUserData(String uid) {
        UserDTO usuario = usuarios.get(uid);
        if (usuario == null) {
            throw new NoSuchElementException("No existe el usuario externo con uid " + uid);
        }
        return usuario;
    }
}
