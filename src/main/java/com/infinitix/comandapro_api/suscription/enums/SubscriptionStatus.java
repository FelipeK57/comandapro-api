package com.infinitix.comandapro_api.suscription.enums;

/**
 * Enumeración que define los posibles estados de una suscripción
 */
public enum SubscriptionStatus {
    
    /**
     * Suscripción activa y funcionando correctamente
     */
    ACTIVA("Activa"),
    
    /**
     * Suscripción temporalmente suspendida
     */
    SUSPENDIDA("Suspendida"),
    
    /**
     * Suscripción que ha expirado o vencido
     */
    VENCIDA("Vencida");
    
    private final String description;
    
    /**
     * Constructor de la enumeración
     * @param description Descripción legible del estado
     */
    SubscriptionStatus(String description) {
        this.description = description;
    }
    
    /**
     * Obtiene la descripción del estado
     * @return Descripción legible del estado
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Verifica si la suscripción está activa
     * @return true si está activa, false en caso contrario
     */
    public boolean isActive() {
        return this == ACTIVA;
    }
    
    /**
     * Verifica si la suscripción está suspendida
     * @return true si está suspendida, false en caso contrario
     */
    public boolean isSuspended() {
        return this == SUSPENDIDA;
    }
    
    /**
     * Verifica si la suscripción está vencida
     * @return true si está vencida, false en caso contrario
     */
    public boolean isExpired() {
        return this == VENCIDA;
    }
}
