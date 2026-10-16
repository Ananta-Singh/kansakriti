package com.kansakriti.user_service.dto;

import lombok.Data;

@Data
public class SellerRegisterRequest {
    private String shopName;
    private String name;
    private String email;
    private String password;
}
