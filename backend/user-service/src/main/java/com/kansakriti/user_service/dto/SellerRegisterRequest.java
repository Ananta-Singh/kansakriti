package com.kansakriti.user_service.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SellerRegisterRequest {
    @NotBlank private String shopName;
    @NotBlank private String name;
    @NotBlank @Email private String email;
    @NotBlank @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
}
