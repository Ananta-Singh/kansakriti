package com.kansakriti.user_service.service;

import com.kansakriti.user_service.dto.SellerRegisterRequest;
import com.kansakriti.user_service.dto.SellerResponse;
import com.kansakriti.user_service.exception.EmailAlreadyExistsException;
import com.kansakriti.user_service.model.Seller;
import com.kansakriti.user_service.model.SellerStatus;
import com.kansakriti.user_service.repositories.SellerRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    private final SellerRepository sellerRepository;
    private final PasswordEncoder passwordEncoder;

    public SellerService(SellerRepository sellerRepository, PasswordEncoder passwordEncoder) {
        this.sellerRepository = sellerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public SellerResponse register(SellerRegisterRequest request) {
        if (sellerRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already registered");
        }

        Seller seller = new Seller();
        seller.setShopName(request.getShopName());
        seller.setName(request.getName());
        seller.setEmail(request.getEmail());
        seller.setPassword(passwordEncoder.encode(request.getPassword()));
        seller.setStatus(SellerStatus.PENDING); // every new seller starts pending admin approval

        Seller saved = sellerRepository.save(seller);

        return new SellerResponse(
                saved.getId(),
                saved.getShopName(),
                saved.getName(),
                saved.getEmail(),
                saved.getRole(),
                saved.getStatus());
    }
}