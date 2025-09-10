package com.infinitix.comandapro_api.auth.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infinitix.comandapro_api.auth.dto.LoginRequestDTO;
import com.infinitix.comandapro_api.auth.dto.RegisterRequestDTO;
import com.infinitix.comandapro_api.auth.service.AuthService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth Controller", description = "APIs for user authentication")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO registerRequest) {
        return ResponseEntity.ok(Map.of("message", authService.register(registerRequest)));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        String token = authService.login(loginRequest);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
