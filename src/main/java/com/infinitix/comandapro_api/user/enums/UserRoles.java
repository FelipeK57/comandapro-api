package com.infinitix.comandapro_api.user.enums;

/**
 * Enumeración que define los posibles roles de usuario
 */
public enum UserRoles {
    /**
     * Usuario administrador
     */
    ADMIN("Administrador"),

    /**
     * Usuario mesero
     */
    MESERO("Mesero"),

    /**
     * Usuario cocinero
     */
    COCINERO("Cocinero");

    private final String description;

    /**
     * Constructor de la enumeración
     * @param description Descripción legible del rol
     */
    UserRoles(String description) {
        this.description = description;
    }

    /**
     * Obtiene la descripción del rol
     * @return Descripción legible del rol
     */
    public String getDescription() {
        return description;
    }

    /**
     * Verifica si el usuario es administrador
     * @return true si es ADMIN, false en caso contrario
     */
    public boolean isAdmin() {
        return this == ADMIN;
    }

    /**
     * Verifica si el usuario es mesero
     * @return true si es MESERO, false en caso contrario
     */
    public boolean isMesero() {
        return this == MESERO;
    }

    /**
     * Verifica si el usuario es cocinero
     * @return true si es COCINERO, false en caso contrario
     */
    public boolean isCocinero() {
        return this == COCINERO;
    }
}
