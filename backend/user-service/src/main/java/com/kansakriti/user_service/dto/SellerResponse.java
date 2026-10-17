package com.kansakriti.user_service.dto;

import com.kansakriti.user_service.model.Role;
import com.kansakriti.user_service.model.SellerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SellerResponse {
    private Long id;
    private String shopName;
    private String name;
    private String email;
    private Role role;
    private SellerStatus status;
}