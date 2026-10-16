package com.kansakriti.user_service.service;

import com.kansakriti.user_service.dto.CustomerRegisterRequest;
import com.kansakriti.user_service.model.Customer;
import com.kansakriti.user_service.repositories.CustomerRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Customer register(CustomerRegisterRequest request) {
        if (customerRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered");
        }

        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        customer.setPhone(request.getPhone());

        return customerRepository.save(customer);
    }
}