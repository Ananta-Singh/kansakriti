package com.kansakriti.user_service.controller;

import com.kansakriti.user_service.dto.CustomerRegisterRequest;
import com.kansakriti.user_service.model.Customer;
import com.kansakriti.user_service.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> register(@RequestBody CustomerRegisterRequest request) {
        Customer customer = customerService.register(request);
        return ResponseEntity.ok(customer);
    }
}
