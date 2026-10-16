package com.kansakriti.user_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "admins")
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password; // store bcrypt hash, never plain text

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.ADMIN;
}
