package com.infinitix.comandapro_api.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.infinitix.comandapro_api.user.dto.CreateUserRequest;
import com.infinitix.comandapro_api.user.entity.User;
import com.infinitix.comandapro_api.user.service.UserService;

/**
 * Controlador REST para la gestión de usuarios
 * Proporciona endpoints para operaciones CRUD y consultas específicas de usuarios
 */
@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:4201")
public class UserController {
    
    @Autowired
    private UserService userService;

    /**
     * Crear un nuevo usuario
     * @param request DTO con los datos del usuario a crear
     * @return Usuario creado
     */
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        try {
            User user = userService.createUser(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error interno del servidor: " + e.getMessage());
        }
    }

    /**
     * Obtener usuario por ID
     * @param id ID del usuario
     * @return Usuario encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Obtener usuario por email
     * @param email Email del usuario
     * @return Usuario encontrado
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Obtener todos los usuarios
     * @return Lista de todos los usuarios
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Obtener usuarios por restaurante
     * @param restaurantId ID del restaurante
     * @return Lista de usuarios del restaurante
     */
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<User>> getUsersByRestaurant(@PathVariable Long restaurantId) {
        List<User> users = userService.getUsersByRestaurant(restaurantId);
        return ResponseEntity.ok(users);
    }

    /**
     * Obtener usuarios activos por restaurante
     * @param restaurantId ID del restaurante
     * @return Lista de usuarios activos del restaurante
     */
    @GetMapping("/restaurant/{restaurantId}/active")
    public ResponseEntity<List<User>> getActiveUsersByRestaurant(@PathVariable Long restaurantId) {
        List<User> users = userService.getActiveUsersByRestaurant(restaurantId);
        return ResponseEntity.ok(users);
    }

    /**
     * Obtener usuarios por rol
     * @param role Rol de los usuarios
     * @return Lista de usuarios con el rol especificado
     */
    @GetMapping("/role/{role}")
    public ResponseEntity<?> getUsersByRole(@PathVariable String role) {
        try {
            List<User> users = userService.getUsersByRole(role);
            return ResponseEntity.ok(users);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    /**
     * Obtener usuarios por rol y restaurante
     * @param role Rol de los usuarios
     * @param restaurantId ID del restaurante
     * @return Lista de usuarios con el rol en el restaurante
     */
    @GetMapping("/role/{role}/restaurant/{restaurantId}")
    public ResponseEntity<?> getUsersByRoleAndRestaurant(@PathVariable String role, @PathVariable Long restaurantId) {
        try {
            List<User> users = userService.getUsersByRoleAndRestaurant(role, restaurantId);
            return ResponseEntity.ok(users);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    /**
     * Actualizar usuario
     * @param id ID del usuario a actualizar
     * @param userData Datos actualizados del usuario
     * @return Usuario actualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User userData) {
        try {
            User updatedUser = userService.updateUser(id, userData);
            if (updatedUser != null) {
                return ResponseEntity.ok(updatedUser);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error interno del servidor: " + e.getMessage());
        }
    }

    /**
     * Activar/desactivar usuario
     * @param id ID del usuario
     * @param active Estado de activación (true/false)
     * @return Usuario actualizado
     */
    @PutMapping("/{id}/status")
    public ResponseEntity<?> toggleUserStatus(@PathVariable Long id, @RequestParam boolean active) {
        User user = userService.toggleUserStatus(id, active);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Eliminar usuario
     * @param id ID del usuario a eliminar
     * @return Respuesta de confirmación
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Verificar si existe un usuario con el email especificado
     * @param email Email a verificar
     * @return true si existe, false en caso contrario
     */
    @GetMapping("/exists/email/{email}")
    public ResponseEntity<Boolean> existsByEmail(@PathVariable String email) {
        boolean exists = userService.existsByEmail(email);
        return ResponseEntity.ok(exists);
    }

    /**
     * Contar usuarios por restaurante
     * @param restaurantId ID del restaurante
     * @return Número de usuarios del restaurante
     */
    @GetMapping("/count/restaurant/{restaurantId}")
    public ResponseEntity<Long> countUsersByRestaurant(@PathVariable Long restaurantId) {
        long count = userService.countUsersByRestaurant(restaurantId);
        return ResponseEntity.ok(count);
    }

    /**
     * Contar usuarios activos por restaurante
     * @param restaurantId ID del restaurante
     * @return Número de usuarios activos del restaurante
     */
    @GetMapping("/count/restaurant/{restaurantId}/active")
    public ResponseEntity<Long> countActiveUsersByRestaurant(@PathVariable Long restaurantId) {
        long count = userService.countActiveUsersByRestaurant(restaurantId);
        return ResponseEntity.ok(count);
    }
}
