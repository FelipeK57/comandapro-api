package com.infinitix.comandapro_api.user.repository;

import com.infinitix.comandapro_api.user.entity.User;
import com.infinitix.comandapro_api.user.enums.UserRoles;
import com.infinitix.comandapro_api.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository para la entidad User
 * Proporciona métodos para realizar operaciones de base de datos relacionadas con usuarios
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Busca usuarios por restaurante
     * @param restaurant El restaurante para filtrar
     * @return Lista de usuarios del restaurante
     */
    List<User> findByRestaurant(Restaurant restaurant);

    /**
     * Busca usuarios por ID del restaurante
     * @param restaurantId ID del restaurante
     * @return Lista de usuarios del restaurante
     */
    List<User> findByRestaurantId(Long restaurantId);

    /**
     * Busca usuarios activos por restaurante
     * @param restaurant El restaurante para filtrar
     * @return Lista de usuarios activos del restaurante
     */
    List<User> findByRestaurantAndActiveTrue(Restaurant restaurant);

    /**
     * Busca usuarios activos por ID del restaurante
     * @param restaurantId ID del restaurante
     * @return Lista de usuarios activos del restaurante
     */
    List<User> findByRestaurantIdAndActiveTrue(Long restaurantId);

    /**
     * Busca usuarios por rol
     * @param role Rol del usuario
     * @return Lista de usuarios con el rol especificado
     */
    List<User> findByRole(UserRoles role);

    /**
     * Busca usuarios por rol y restaurante
     * @param role Rol del usuario
     * @param restaurant El restaurante para filtrar
     * @return Lista de usuarios con el rol en el restaurante
     */
    List<User> findByRoleAndRestaurant(UserRoles role, Restaurant restaurant);

    /**
     * Busca usuarios por rol y ID del restaurante
     * @param role Rol del usuario
     * @param restaurantId ID del restaurante
     * @return Lista de usuarios con el rol en el restaurante
     */
    List<User> findByRoleAndRestaurantId(UserRoles role, Long restaurantId);

    /**
     * Busca usuarios activos por rol
     * @param role Rol del usuario
     * @return Lista de usuarios activos con el rol especificado
     */
    List<User> findByRoleAndActiveTrue(UserRoles role);

    /**
     * Busca usuarios activos por rol y restaurante
     * @param role Rol del usuario
     * @param restaurant El restaurante para filtrar
     * @return Lista de usuarios activos con el rol en el restaurante
     */
    List<User> findByRoleAndRestaurantAndActiveTrue(UserRoles role, Restaurant restaurant);

    /**
     * Busca usuarios activos por rol y ID del restaurante
     * @param role Rol del usuario
     * @param restaurantId ID del restaurante
     * @return Lista de usuarios activos con el rol en el restaurante
     */
    List<User> findByRoleAndRestaurantIdAndActiveTrue(UserRoles role, Long restaurantId);

    /**
     * Busca un usuario por email
     * @param email Email del usuario
     * @return Optional con el usuario si existe
     */
    Optional<User> findByEmail(String email);

    /**
     * Busca un usuario activo por email
     * @param email Email del usuario
     * @return Optional con el usuario activo si existe
     */
    Optional<User> findByEmailAndActiveTrue(String email);

    /**
     * Busca un usuario por email y restaurante
     * @param email Email del usuario
     * @param restaurant El restaurante para filtrar
     * @return Optional con el usuario si existe en el restaurante
     */
    Optional<User> findByEmailAndRestaurant(String email, Restaurant restaurant);

    /**
     * Busca un usuario activo por email y restaurante
     * @param email Email del usuario
     * @param restaurant El restaurante para filtrar
     * @return Optional con el usuario activo si existe en el restaurante
     */
    Optional<User> findByEmailAndRestaurantAndActiveTrue(String email, Restaurant restaurant);

    /**
     * Busca un usuario por email y ID del restaurante
     * @param email Email del usuario
     * @param restaurantId ID del restaurante
     * @return Optional con el usuario si existe en el restaurante
     */
    Optional<User> findByEmailAndRestaurantId(String email, Long restaurantId);

    /**
     * Busca un usuario activo por email y ID del restaurante
     * @param email Email del usuario
     * @param restaurantId ID del restaurante
     * @return Optional con el usuario activo si existe en el restaurante
     */
    Optional<User> findByEmailAndRestaurantIdAndActiveTrue(String email, Long restaurantId);

    /**
     * Busca usuarios por nombre completo (búsqueda parcial, case insensitive)
     * @param fullName Nombre completo o parte del nombre
     * @return Lista de usuarios que coinciden con el nombre
     */
    List<User> findByFullNameContainingIgnoreCase(String fullName);

    /**
     * Busca usuarios por nombre completo en un restaurante específico
     * @param fullName Nombre completo o parte del nombre
     * @param restaurantId ID del restaurante
     * @return Lista de usuarios que coinciden con el nombre en el restaurante
     */
    List<User> findByFullNameContainingIgnoreCaseAndRestaurantId(String fullName, Long restaurantId);

    /**
     * Busca usuarios activos por nombre completo
     * @param fullName Nombre completo o parte del nombre
     * @return Lista de usuarios activos que coinciden con el nombre
     */
    List<User> findByFullNameContainingIgnoreCaseAndActiveTrue(String fullName);

    /**
     * Busca usuarios activos por nombre completo en un restaurante específico
     * @param fullName Nombre completo o parte del nombre
     * @param restaurantId ID del restaurante
     * @return Lista de usuarios activos que coinciden con el nombre en el restaurante
     */
    List<User> findByFullNameContainingIgnoreCaseAndRestaurantIdAndActiveTrue(String fullName, Long restaurantId);

    /**
     * Verifica si existe un usuario con el email especificado
     * @param email Email a verificar
     * @return true si existe, false en caso contrario
     */
    boolean existsByEmail(String email);

    /**
     * Verifica si existe un usuario activo con el email especificado
     * @param email Email a verificar
     * @return true si existe y está activo, false en caso contrario
     */
    boolean existsByEmailAndActiveTrue(String email);

    /**
     * Verifica si existe un usuario con el email en el restaurante especificado
     * @param email Email a verificar
     * @param restaurantId ID del restaurante
     * @return true si existe en el restaurante, false en caso contrario
     */
    boolean existsByEmailAndRestaurantId(String email, Long restaurantId);

    /**
     * Verifica si existe un usuario activo con el email en el restaurante especificado
     * @param email Email a verificar
     * @param restaurantId ID del restaurante
     * @return true si existe y está activo en el restaurante, false en caso contrario
     */
    boolean existsByEmailAndRestaurantIdAndActiveTrue(String email, Long restaurantId);

    /**
     * Cuenta usuarios por restaurante
     * @param restaurantId ID del restaurante
     * @return Número de usuarios del restaurante
     */
    long countByRestaurantId(Long restaurantId);

    /**
     * Cuenta usuarios activos por restaurante
     * @param restaurantId ID del restaurante
     * @return Número de usuarios activos del restaurante
     */
    long countByRestaurantIdAndActiveTrue(Long restaurantId);

    /**
     * Cuenta usuarios por rol
     * @param role Rol del usuario
     * @return Número de usuarios con el rol especificado
     */
    long countByRole(UserRoles role);

    /**
     * Cuenta usuarios por rol y restaurante
     * @param role Rol del usuario
     * @param restaurantId ID del restaurante
     * @return Número de usuarios con el rol en el restaurante
     */
    long countByRoleAndRestaurantId(UserRoles role, Long restaurantId);

    /**
     * Cuenta usuarios activos por rol
     * @param role Rol del usuario
     * @return Número de usuarios activos con el rol especificado
     */
    long countByRoleAndActiveTrue(UserRoles role);

    /**
     * Cuenta usuarios activos por rol y restaurante
     * @param role Rol del usuario
     * @param restaurantId ID del restaurante
     * @return Número de usuarios activos con el rol en el restaurante
     */
    long countByRoleAndRestaurantIdAndActiveTrue(UserRoles role, Long restaurantId);

    /**
     * Consulta personalizada para buscar usuarios por múltiples criterios
     * @param restaurantId ID del restaurante (opcional)
     * @param role Rol del usuario (opcional)
     * @param active Estado de activación (opcional)
     * @param fullName Nombre completo o parte del nombre (opcional)
     * @return Lista de usuarios que coinciden con los criterios
     */
    @Query("SELECT u FROM User u WHERE " +
           "(:restaurantId IS NULL OR u.restaurant.id = :restaurantId) AND " +
           "(:role IS NULL OR u.role = :role) AND " +
           "(:active IS NULL OR u.active = :active) AND " +
           "(:fullName IS NULL OR LOWER(u.fullName) LIKE LOWER(CONCAT('%', :fullName, '%')))")
    List<User> findUsersByFilters(@Param("restaurantId") Long restaurantId,
                                  @Param("role") UserRoles role,
                                  @Param("active") Boolean active,
                                  @Param("fullName") String fullName);

    /**
     * Consulta personalizada para buscar usuarios por email (búsqueda parcial)
     * @param email Email o parte del email
     * @param restaurantId ID del restaurante (opcional)
     * @return Lista de usuarios que coinciden con el email
     */
    @Query("SELECT u FROM User u WHERE " +
           "LOWER(u.email) LIKE LOWER(CONCAT('%', :email, '%')) AND " +
           "(:restaurantId IS NULL OR u.restaurant.id = :restaurantId)")
    List<User> findUsersByEmailContaining(@Param("email") String email,
                                          @Param("restaurantId") Long restaurantId);

    /**
     * Consulta personalizada para obtener estadísticas de usuarios por restaurante
     * @param restaurantId ID del restaurante
     * @return Array con [total, activos, inactivos, por_rol]
     */
    @Query("SELECT " +
           "COUNT(u), " +
           "SUM(CASE WHEN u.active = true THEN 1 ELSE 0 END), " +
           "SUM(CASE WHEN u.active = false THEN 1 ELSE 0 END), " +
           "u.role " +
           "FROM User u WHERE u.restaurant.id = :restaurantId " +
           "GROUP BY u.role")
    List<Object[]> getUserStatisticsByRestaurant(@Param("restaurantId") Long restaurantId);
}
