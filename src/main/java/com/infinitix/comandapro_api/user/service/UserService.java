package com.infinitix.comandapro_api.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infinitix.comandapro_api.user.dto.CreateUserRequest;
import com.infinitix.comandapro_api.user.entity.User;
import com.infinitix.comandapro_api.user.repository.UserRepository;
import com.infinitix.comandapro_api.restaurant.entity.Restaurant;
import com.infinitix.comandapro_api.restaurant.repository.RestaurantRepository;

/**
 * Servicio para la gestión de usuarios
 * Proporciona métodos para operaciones CRUD y lógica de negocio relacionada con usuarios
 */
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RestaurantRepository restaurantRepository;

    /**
     * Crear usuario desde DTO
     * @param request DTO con los datos del usuario a crear
     * @return Usuario creado
     * @throws IllegalArgumentException si el restaurante no existe o el email ya está en uso
     */
    public User createUser(CreateUserRequest request) {
        // Buscar el restaurante
        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
            .orElseThrow(() -> new IllegalArgumentException("Restaurante no encontrado con ID: " + request.getRestaurantId()));
        
        // Verificar si el email ya existe
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Ya existe un usuario con el email: " + request.getEmail());
        }
        
        // Crear el usuario
        User user = new User();
        user.setRestaurant(restaurant);
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // En producción, debería estar encriptada
        user.setRole(request.getRole());
        user.setActive(request.isActive());
        
        return userRepository.save(user);
    }

    /**
     * Crear usuario (método para compatibilidad)
     * @param user Usuario a crear
     * @return Usuario creado
     * @throws IllegalArgumentException si los campos obligatorios no están presentes
     */
    public User saveUser(User user) {
        // Validar que los campos obligatorios estén presentes
        if (user.getFullName() == null || user.getFullName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre completo es obligatorio");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("El email es obligatorio");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }
        if (user.getRole() == null) {
            throw new IllegalArgumentException("El rol es obligatorio");
        }
        if (user.getRestaurant() == null) {
            throw new IllegalArgumentException("El restaurante es obligatorio");
        }
        
        // Verificar si el email ya existe
        if (user.getId() == null && userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Ya existe un usuario con el email: " + user.getEmail());
        }
        
        return userRepository.save(user);
    }

    /**
     * Obtener usuario por ID
     * @param id ID del usuario
     * @return Usuario encontrado o null si no existe
     */
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Obtener usuario por email
     * @param email Email del usuario
     * @return Usuario encontrado o null si no existe
     */
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    /**
     * Obtener todos los usuarios
     * @return Lista de todos los usuarios
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    /**
     * Obtener usuarios por restaurante
     * @param restaurantId ID del restaurante
     * @return Lista de usuarios del restaurante
     */
    public List<User> getUsersByRestaurant(Long restaurantId) {
        return userRepository.findByRestaurantId(restaurantId);
    }
    
    /**
     * Obtener usuarios activos por restaurante
     * @param restaurantId ID del restaurante
     * @return Lista de usuarios activos del restaurante
     */
    public List<User> getActiveUsersByRestaurant(Long restaurantId) {
        return userRepository.findByRestaurantIdAndActiveTrue(restaurantId);
    }

    /**
     * Obtener usuarios por rol
     * @param role Rol de los usuarios
     * @return Lista de usuarios con el rol especificado
     */
    public List<User> getUsersByRole(String role) {
        try {
            return userRepository.findByRole(com.infinitix.comandapro_api.user.enums.UserRoles.valueOf(role.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Rol no válido: " + role);
        }
    }

    /**
     * Obtener usuarios por rol y restaurante
     * @param role Rol de los usuarios
     * @param restaurantId ID del restaurante
     * @return Lista de usuarios con el rol en el restaurante
     */
    public List<User> getUsersByRoleAndRestaurant(String role, Long restaurantId) {
        try {
            return userRepository.findByRoleAndRestaurantId(
                com.infinitix.comandapro_api.user.enums.UserRoles.valueOf(role.toUpperCase()), 
                restaurantId
            );
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Rol no válido: " + role);
        }
    }
    
    /**
     * Actualizar usuario
     * @param id ID del usuario a actualizar
     * @param userData Datos actualizados del usuario
     * @return Usuario actualizado o null si no existe
     */
    public User updateUser(Long id, User userData) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            // Verificar si el email ya existe en otro usuario
            if (userData.getEmail() != null && !userData.getEmail().equals(existingUser.getEmail())) {
                if (userRepository.existsByEmail(userData.getEmail())) {
                    throw new IllegalArgumentException("Ya existe un usuario con el email: " + userData.getEmail());
                }
            }
            
            // Actualizar campos si se proporcionan
            if (userData.getFullName() != null) {
                existingUser.setFullName(userData.getFullName());
            }
            if (userData.getEmail() != null) {
                existingUser.setEmail(userData.getEmail());
            }
            if (userData.getPassword() != null) {
                existingUser.setPassword(userData.getPassword()); // En producción, debería estar encriptada
            }
            if (userData.getRole() != null) {
                existingUser.setRole(userData.getRole());
            }
            // El campo active siempre se puede actualizar
            existingUser.setActive(userData.isActive());
            
            return userRepository.save(existingUser);
        }
        return null;
    }

    /**
     * Eliminar usuario
     * @param id ID del usuario a eliminar
     * @return true si se eliminó correctamente, false si no existe
     */
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Activar/desactivar usuario
     * @param id ID del usuario
     * @param active Estado de activación
     * @return Usuario actualizado o null si no existe
     */
    public User toggleUserStatus(Long id, boolean active) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setActive(active);
            return userRepository.save(user);
        }
        return null;
    }

    /**
     * Verificar si existe un usuario con el email especificado
     * @param email Email a verificar
     * @return true si existe, false en caso contrario
     */
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * Contar usuarios por restaurante
     * @param restaurantId ID del restaurante
     * @return Número de usuarios del restaurante
     */
    public long countUsersByRestaurant(Long restaurantId) {
        return userRepository.countByRestaurantId(restaurantId);
    }

    /**
     * Contar usuarios activos por restaurante
     * @param restaurantId ID del restaurante
     * @return Número de usuarios activos del restaurante
     */
    public long countActiveUsersByRestaurant(Long restaurantId) {
        return userRepository.countByRestaurantIdAndActiveTrue(restaurantId);
    }
}
