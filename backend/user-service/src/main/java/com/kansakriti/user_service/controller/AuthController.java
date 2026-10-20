package com.kansakriti.user_service.controller;

import com.kansakriti.user_service.dto.LoginRequest;
import com.kansakriti.user_service.dto.LoginResponse;
import com.kansakriti.user_service.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/login")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/customer")
    public ResponseEntity<LoginResponse> loginCustomer(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.loginCustomer(request));
    }

    @PostMapping("/seller")
    public ResponseEntity<LoginResponse> loginSeller(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.loginSeller(request));
    }

    @PostMapping("/admin")
    public ResponseEntity<LoginResponse> loginAdmin(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.loginAdmin(request));
    }
}