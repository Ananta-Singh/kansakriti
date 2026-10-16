package com.kansakriti.user_service.dto;

import lombok.Data;

@Data
public class CustomerRegisterRequest {
    private String name;
    private String email;
    private String password;
    private String phone;
}
