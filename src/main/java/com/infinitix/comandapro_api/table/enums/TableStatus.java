package com.infinitix.comandapro_api.table.enums;

/**
 * Enumeración que define los posibles estados de una mesa
 */
public enum TableStatus {
    /**
     * Mesa disponible para ser ocupada
     */
    DISPONIBLE("Disponible"),

    /**
     * Mesa actualmente ocupada
     */
    OCUPADA("Ocupada");

    private final String description;

    /**
     * Constructor de la enumeración
     * 
     * @param description Descripción legible del estado
     */
    TableStatus(String description) {
        this.description = description;
    }

    /**
     * Obtiene la descripción del estado
     * 
     * @return Descripción legible del estado
     */
    public String getDescription() {
        return description;
    }

    /**
     * Verifica si la mesa está disponible
     * 
     * @return true si está disponible, false en caso contrario
     */
    public boolean isDisponible() {
        return this == DISPONIBLE;
    }

    /**
     * Verifica si la mesa está ocupada
     * 
     * @return true si está ocupada, false en caso contrario
     */
    public boolean isOcupada() {
        return this == OCUPADA;
    }

}
