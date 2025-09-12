package com.infinitix.comandapro_api.product.repository;

import com.infinitix.comandapro_api.product.entitiy.Product;
import com.infinitix.comandapro_api.product.enums.ProductCategory;
import com.infinitix.comandapro_api.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository para la entidad Product
 * Proporciona métodos para realizar operaciones de base de datos relacionadas con productos
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Busca productos por restaurante
     * @param restaurant El restaurante para filtrar
     * @return Lista de productos del restaurante
     */
    List<Product> findByRestaurant(Restaurant restaurant);

    /**
     * Busca productos por ID del restaurante
     * @param restaurantId ID del restaurante
     * @return Lista de productos del restaurante
     */
    List<Product> findByRestaurantId(Long restaurantId);

    /**
     * Busca productos disponibles por restaurante
     * @param restaurant El restaurante para filtrar
     * @return Lista de productos disponibles del restaurante
     */
    List<Product> findByRestaurantAndAvailableTrue(Restaurant restaurant);

    /**
     * Busca productos disponibles por ID del restaurante
     * @param restaurantId ID del restaurante
     * @return Lista de productos disponibles del restaurante
     */
    List<Product> findByRestaurantIdAndAvailableTrue(Long restaurantId);

    /**
     * Busca productos por categoría
     * @param category Categoría del producto
     * @return Lista de productos de la categoría
     */
    List<Product> findByCategory(ProductCategory category);

    /**
     * Busca productos por categoría y restaurante
     * @param category Categoría del producto
     * @param restaurant El restaurante para filtrar
     * @return Lista de productos de la categoría en el restaurante
     */
    List<Product> findByCategoryAndRestaurant(ProductCategory category, Restaurant restaurant);

    /**
     * Busca productos por categoría y ID del restaurante
     * @param category Categoría del producto
     * @param restaurantId ID del restaurante
     * @return Lista de productos de la categoría en el restaurante
     */
    List<Product> findByCategoryAndRestaurantId(ProductCategory category, Long restaurantId);

    /**
     * Busca productos disponibles por categoría
     * @param category Categoría del producto
     * @return Lista de productos disponibles de la categoría
     */
    List<Product> findByCategoryAndAvailableTrue(ProductCategory category);

    /**
     * Busca productos disponibles por categoría y restaurante
     * @param category Categoría del producto
     * @param restaurant El restaurante para filtrar
     * @return Lista de productos disponibles de la categoría en el restaurante
     */
    List<Product> findByCategoryAndRestaurantAndAvailableTrue(ProductCategory category, Restaurant restaurant);

    /**
     * Busca productos disponibles por categoría y ID del restaurante
     * @param category Categoría del producto
     * @param restaurantId ID del restaurante
     * @return Lista de productos disponibles de la categoría en el restaurante
     */
    List<Product> findByCategoryAndRestaurantIdAndAvailableTrue(ProductCategory category, Long restaurantId);

    /**
     * Busca productos por nombre (búsqueda parcial, case insensitive)
     * @param name Nombre o parte del nombre del producto
     * @return Lista de productos que coinciden con el nombre
     */
    List<Product> findByNameContainingIgnoreCase(String name);

    /**
     * Busca productos por nombre en un restaurante específico
     * @param name Nombre o parte del nombre del producto
     * @param restaurantId ID del restaurante
     * @return Lista de productos que coinciden con el nombre en el restaurante
     */
    List<Product> findByNameContainingIgnoreCaseAndRestaurantId(String name, Long restaurantId);

    /**
     * Busca productos por rango de precios
     * @param minPrice Precio mínimo
     * @param maxPrice Precio máximo
     * @return Lista de productos en el rango de precios
     */
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    /**
     * Busca productos por rango de precios en un restaurante específico
     * @param minPrice Precio mínimo
     * @param maxPrice Precio máximo
     * @param restaurantId ID del restaurante
     * @return Lista de productos en el rango de precios del restaurante
     */
    List<Product> findByPriceBetweenAndRestaurantId(Double minPrice, Double maxPrice, Long restaurantId);

    /**
     * Busca productos por precio menor o igual
     * @param maxPrice Precio máximo
     * @return Lista de productos con precio menor o igual al especificado
     */
    List<Product> findByPriceLessThanEqual(Double maxPrice);

    /**
     * Busca productos por precio mayor o igual
     * @param minPrice Precio mínimo
     * @return Lista de productos con precio mayor o igual al especificado
     */
    List<Product> findByPriceGreaterThanEqual(Double minPrice);

    /**
     * Busca productos disponibles por precio menor o igual
     * @param maxPrice Precio máximo
     * @return Lista de productos disponibles con precio menor o igual al especificado
     */
    List<Product> findByPriceLessThanEqualAndAvailableTrue(Double maxPrice);

    /**
     * Busca productos disponibles por precio mayor o igual
     * @param minPrice Precio mínimo
     * @return Lista de productos disponibles con precio mayor o igual al especificado
     */
    List<Product> findByPriceGreaterThanEqualAndAvailableTrue(Double minPrice);

    /**
     * Busca un producto por nombre exacto en un restaurante específico
     * @param name Nombre exacto del producto
     * @param restaurantId ID del restaurante
     * @return Optional con el producto si existe
     */
    Optional<Product> findByNameAndRestaurantId(String name, Long restaurantId);

    /**
     * Cuenta productos por restaurante
     * @param restaurantId ID del restaurante
     * @return Número de productos del restaurante
     */
    long countByRestaurantId(Long restaurantId);

    /**
     * Cuenta productos disponibles por restaurante
     * @param restaurantId ID del restaurante
     * @return Número de productos disponibles del restaurante
     */
    long countByRestaurantIdAndAvailableTrue(Long restaurantId);

    /**
     * Cuenta productos por categoría
     * @param category Categoría del producto
     * @return Número de productos de la categoría
     */
    long countByCategory(ProductCategory category);

    /**
     * Cuenta productos por categoría y restaurante
     * @param category Categoría del producto
     * @param restaurantId ID del restaurante
     * @return Número de productos de la categoría en el restaurante
     */
    long countByCategoryAndRestaurantId(ProductCategory category, Long restaurantId);

    /**
     * Consulta personalizada para buscar productos por múltiples criterios
     * @param restaurantId ID del restaurante (opcional)
     * @param category Categoría del producto (opcional)
     * @param minPrice Precio mínimo (opcional)
     * @param maxPrice Precio máximo (opcional)
     * @param available Disponibilidad (opcional)
     * @return Lista de productos que coinciden con los criterios
     */
    @Query("SELECT p FROM Product p WHERE " +
           "(:restaurantId IS NULL OR p.restaurant.id = :restaurantId) AND " +
           "(:category IS NULL OR p.category = :category) AND " +
           "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
           "(:maxPrice IS NULL OR p.price <= :maxPrice) AND " +
           "(:available IS NULL OR p.available = :available)")
    List<Product> findProductsByFilters(@Param("restaurantId") Long restaurantId,
                                       @Param("category") ProductCategory category,
                                       @Param("minPrice") Double minPrice,
                                       @Param("maxPrice") Double maxPrice,
                                       @Param("available") Boolean available);

    /**
     * Consulta personalizada para buscar productos por nombre (búsqueda parcial)
     * @param name Nombre o parte del nombre del producto
     * @param restaurantId ID del restaurante (opcional)
     * @return Lista de productos que coinciden con el nombre
     */
    @Query("SELECT p FROM Product p WHERE " +
           "LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')) AND " +
           "(:restaurantId IS NULL OR p.restaurant.id = :restaurantId)")
    List<Product> findProductsByNameContaining(@Param("name") String name,
                                              @Param("restaurantId") Long restaurantId);

    /**
     * Consulta personalizada para obtener estadísticas de productos por restaurante
     * @param restaurantId ID del restaurante
     * @return Array con [total, disponibles, no_disponibles, precio_promedio, precio_min, precio_max]
     */
    @Query("SELECT " +
           "COUNT(p), " +
           "SUM(CASE WHEN p.available = true THEN 1 ELSE 0 END), " +
           "SUM(CASE WHEN p.available = false THEN 1 ELSE 0 END), " +
           "AVG(p.price), " +
           "MIN(p.price), " +
           "MAX(p.price) " +
           "FROM Product p WHERE p.restaurant.id = :restaurantId")
    Object[] getProductStatisticsByRestaurant(@Param("restaurantId") Long restaurantId);
}
