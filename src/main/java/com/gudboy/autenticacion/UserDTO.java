package com.gudboy.autenticacion;

// Estructura que devuelve el servicio externo de autenticacion (no modificable).
public class UserDTO {

    private final String uid;
    private final String fullName;
    private final String email;
    private final String role;

    public UserDTO(String uid, String fullName, String email, String role) {
        this.uid = uid;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
    }

    public String getUid() {
        return uid;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}
