package com.gudboy.autenticacion;

import com.gudboy.usuario.Usuario;
import com.gudboy.usuario.Veterinario;
import com.gudboy.usuario.Visitador;

// Adapter: traduce la interfaz del servicio externo a la que espera nuestro sistema.
public class AdaptadorAutenticacion implements IAutenticacion {

    private final AuthServiceExterno servicioExterno;

    public AdaptadorAutenticacion(AuthServiceExterno servicioExterno) {
        this.servicioExterno = servicioExterno;
    }

    @Override
    public Usuario obtenerUsuario(String id) {
        UserDTO dto = servicioExterno.getUserData(id);
        return switch (dto.getRole()) {
            case "VETERINARIO" -> new Veterinario(dto.getUid(), dto.getFullName(), dto.getEmail());
            case "VISITADOR" -> new Visitador(dto.getUid(), dto.getFullName());
            default -> throw new IllegalArgumentException("Rol no soportado: " + dto.getRole());
        };
    }
}
