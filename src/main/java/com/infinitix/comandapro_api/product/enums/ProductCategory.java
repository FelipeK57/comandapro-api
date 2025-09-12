package com.infinitix.comandapro_api.product.enums;

/**
 * Enumeración que define las posibles categorías de productos
 */
public enum ProductCategory {
	/**
	 * Entradas
	 */
	ENTRADAS("Entradas"),

	/**
	 * Platos principales
	 */
	PLATOS_PRINCIPALES("Platos principales"),

	/**
	 * Ensaladas
	 */
	ENSALADAS("Ensaladas"),

	/**
	 * Pescados
	 */
	PESCADOS("Pescados"),

	/**
	 * Postres
	 */
	POSTRES("Postres"),

	/**
	 * Bebidas
	 */
	BEBIDAS("Bebidas"),

	/**
	 * Pastas
	 */
	PASTAS("Pastas"),

	/**
	 * Licores
	 */
	LICORES("Licores"),

	/**
	 * Adicionales
	 */
	ADICIONALES("Adicionales");

	private final String description;

	/**
	 * Constructor de la enumeración
	 * @param description Descripción legible de la categoría
	 */
	ProductCategory(String description) {
		this.description = description;
	}

	/**
	 * Obtiene la descripción de la categoría
	 * @return Descripción legible de la categoría
	 */
	public String getDescription() {
		return description;
	}

	// Métodos de verificación para cada categoría
	public boolean isEntradas() { return this == ENTRADAS; }
	public boolean isPlatosPrincipales() { return this == PLATOS_PRINCIPALES; }
	public boolean isEnsaladas() { return this == ENSALADAS; }
	public boolean isPescados() { return this == PESCADOS; }
	public boolean isPostres() { return this == POSTRES; }
	public boolean isBebidas() { return this == BEBIDAS; }
	public boolean isPastas() { return this == PASTAS; }
	public boolean isLicores() { return this == LICORES; }
	public boolean isAdicionales() { return this == ADICIONALES; }
}
