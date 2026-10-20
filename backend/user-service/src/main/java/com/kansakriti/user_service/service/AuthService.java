package com.kansakriti.user_service.service;

import com.kansakriti.user_service.dto.LoginRequest;
import com.kansakriti.user_service.dto.LoginResponse;
import com.kansakriti.user_service.exception.AccountNotActiveException;
import com.kansakriti.user_service.exception.InvalidCredentialsException;
import com.kansakriti.user_service.model.Admin;
import com.kansakriti.user_service.model.Customer;
import com.kansakriti.user_service.model.Role;
import com.kansakriti.user_service.model.Seller;
import com.kansakriti.user_service.model.SellerStatus;
import com.kansakriti.user_service.repositories.AdminRepository;
import com.kansakriti.user_service.repositories.CustomerRepository;
import com.kansakriti.user_service.repositories.SellerRepository;
import com.kansakriti.user_service.security.JwtService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(CustomerRepository customerRepository,
                       SellerRepository sellerRepository,
                       AdminRepository adminRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.customerRepository = customerRepository;
        this.sellerRepository = sellerRepository;
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public LoginResponse loginCustomer(LoginRequest request) {
        Customer customer = customerRepository.findByEmail(request.getEmail())
                .orElseThrow(InvalidCredentialsException::new);
        checkPassword(request.getPassword(), customer.getPassword());
        String token = jwtService.generateToken(customer.getId(), customer.getEmail(), Role.CUSTOMER);
        return new LoginResponse(token, customer.getId(), Role.CUSTOMER);
    }

    public LoginResponse loginSeller(LoginRequest request) {
        Seller seller = sellerRepository.findByEmail(request.getEmail())
                .orElseThrow(InvalidCredentialsException::new);
        checkPassword(request.getPassword(), seller.getPassword());
        if (seller.getStatus() == SellerStatus.PENDING) {
            throw new AccountNotActiveException("Your seller account is pending admin approval");
        }
        if (seller.getStatus() != SellerStatus.ACTIVE) {
            throw new AccountNotActiveException("Your seller account is not active");
        }
        String token = jwtService.generateToken(seller.getId(), seller.getEmail(), Role.SELLER);
        return new LoginResponse(token, seller.getId(), Role.SELLER);
    }

    public LoginResponse loginAdmin(LoginRequest request) {
        Admin admin = adminRepository.findByEmail(request.getEmail())
                .orElseThrow(InvalidCredentialsException::new);
        checkPassword(request.getPassword(), admin.getPassword());
        String token = jwtService.generateToken(admin.getId(), admin.getEmail(), Role.ADMIN);
        return new LoginResponse(token, admin.getId(), Role.ADMIN);
    }

    // Same exception for unknown email and wrong password, so it does not reveal which one failed.
    private void checkPassword(String raw, String hash) {
        if (!passwordEncoder.matches(raw, hash)) {
            throw new InvalidCredentialsException();
        }
    }
}