package com.infinitix.comandapro_api.order.enums;

/**
 * Enumeración que define los posibles estados de una orden
 */
public enum OrderStatus {
    /**
     * Orden pendiente por preparar
     */
    PENDIENTE("Pendiente"),

    /**
     * Orden en proceso de preparación
     */
    EN_PREPARACION("En preparación"),

    /**
     * Orden lista para ser entregada
     */
    LISTO("Listo"),

    /**
     * Orden entregada al cliente
     */
    ENTREGADO("Entregado"),

    /**
     * Orden cancelada
     */
    CANCELADO("Cancelado"),

    /**
     * Orden pagada
     */
    PAGADO("Pagado");

    private final String description;

    /**
     * Constructor de la enumeración
     * 
     * @param description Descripción legible del estado
     */
    OrderStatus(String description) {
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

    public boolean isPendiente() {
        return this == PENDIENTE;
    }

    public boolean isEnPreparacion() {
        return this == EN_PREPARACION;
    }

    public boolean isListo() {
        return this == LISTO;
    }

    public boolean isEntregado() {
        return this == ENTREGADO;
    }

    public boolean isCancelado() {
        return this == CANCELADO;
    }

    public boolean isPagado() {
        return this == PAGADO;
    }
}
