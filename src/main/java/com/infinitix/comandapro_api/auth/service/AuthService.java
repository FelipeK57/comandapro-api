package com.infinitix.comandapro_api.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.infinitix.comandapro_api.auth.dto.LoginRequestDTO;
import com.infinitix.comandapro_api.auth.dto.RegisterRequestDTO;
import com.infinitix.comandapro_api.auth.exception.AuthException;
import com.infinitix.comandapro_api.restaurant.entity.Restaurant;
import com.infinitix.comandapro_api.restaurant.repository.RestaurantRepository;
import com.infinitix.comandapro_api.security.JwtUtil;
import com.infinitix.comandapro_api.user.entity.User;
import com.infinitix.comandapro_api.user.enums.UserRoles;
import com.infinitix.comandapro_api.user.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public String register(RegisterRequestDTO registerRequest) {
        String fullName = registerRequest.getFullName();
        String restaurantName = registerRequest.getRestaurantName();
        String email = registerRequest.getEmail();
        String password = registerRequest.getPassword();

        if (fullName == null || fullName.isBlank() || restaurantName == null || restaurantName.isBlank() || email == null || email.isBlank() || password == null || password.isBlank()) {
            throw new AuthException("Todos los campos son obligatorios");
        }

        Restaurant restaurantExist = restaurantRepository.findByName(restaurantName).orElse(null);
        if (restaurantExist != null) {
            throw new AuthException("El nombre del restaurante ya está en uso");
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new AuthException("El correo electrónico ya está en uso");
        }

        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantName);
        restaurant.setActive(true);
        restaurantRepository.save(restaurant);

        User newUser = new User();
        newUser.setFullName(fullName);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRole(UserRoles.ADMIN);
        newUser.setActive(true);
        newUser.setRestaurant(restaurant);
        userRepository.save(newUser);

        return "Registro completado exitosamente";
    }

    public String login(LoginRequestDTO loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        Boolean rememberMe = loginRequest.getRememberMe();

        if (email == null || email.isBlank() || password == null || password.isBlank()) {
            throw new AuthException("Correo y contraseña son obligatorios");
        }

        User userExists = userRepository.findByEmail(email).orElse(null);
        if (userExists == null) {
            throw new AuthException("Credenciales inválidas");
        }

        if (!passwordEncoder.matches(password, userExists.getPassword())) {
            throw new AuthException("Credenciales inválidas");
        }

        long expiration = (rememberMe != null && rememberMe) ? 30L * 24 * 60 * 60 * 1000 : 24 * 60 * 60 * 1000;
        Long restaurantId = userExists.getRestaurant().getId();
        String token = JwtUtil.generateToken(userExists.getFullName(), restaurantId, expiration);
        return token;
    }
}
