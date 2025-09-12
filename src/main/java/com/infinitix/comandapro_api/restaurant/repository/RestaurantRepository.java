package com.infinitix.comandapro_api.restaurant.repository;

import com.infinitix.comandapro_api.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository para la entidad Restaurant
 * Proporciona métodos para realizar operaciones de base de datos relacionadas con restaurantes
 */
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    /**
     * Busca restaurantes activos
     * @return Lista de restaurantes activos
     */
    List<Restaurant> findByActiveTrue();

    /**
     * Busca restaurantes inactivos
     * @return Lista de restaurantes inactivos
     */
    List<Restaurant> findByActiveFalse();

    /**
     * Busca un restaurante por nombre exacto
     * @param name Nombre del restaurante
     * @return Optional con el restaurante si existe
     */
    Optional<Restaurant> findByName(String name);

    /**
     * Busca un restaurante por email
     * @param email Email del restaurante
     * @return Optional con el restaurante si existe
     */
    Optional<Restaurant> findByEmail(String email);

    /**
     * Verifica si existe un restaurante con el nombre especificado
     * @param name Nombre del restaurante
     * @return true si existe, false si no
     */
    boolean existsByName(String name);

    /**
     * Verifica si existe un restaurante con el email especificado
     * @param email Email del restaurante
     * @return true si existe, false si no
     */
    boolean existsByEmail(String email);

    /**
     * Cuenta restaurantes activos
     * @return Número de restaurantes activos
     */
    long countByActiveTrue();

    /**
     * Cuenta restaurantes inactivos
     * @return Número de restaurantes inactivos
     */
    long countByActiveFalse();
}
