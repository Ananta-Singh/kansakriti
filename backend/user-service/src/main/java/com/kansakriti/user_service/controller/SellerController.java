package com.kansakriti.user_service.controller;

import com.kansakriti.user_service.dto.SellerRegisterRequest;
import com.kansakriti.user_service.dto.SellerResponse;
import com.kansakriti.user_service.service.SellerService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @PostMapping("/register")
    public ResponseEntity<SellerResponse> register(@Valid @RequestBody SellerRegisterRequest request) {
        SellerResponse response = sellerService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}