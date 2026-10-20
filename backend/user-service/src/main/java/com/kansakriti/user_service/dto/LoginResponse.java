package com.kansakriti.user_service.dto;

import com.kansakriti.user_service.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private Long userId;
    private Role role;
}