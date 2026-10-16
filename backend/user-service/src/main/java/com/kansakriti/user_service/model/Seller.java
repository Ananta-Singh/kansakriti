package com.kansakriti.user_service.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sellers")
@Data
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String shopName;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.SELLER;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SellerStatus status = SellerStatus.PENDING;

    private String stripeAccountId;       // set once they start Stripe Connect onboarding
    private boolean stripeOnboarded = false; // true once Stripe account is fully active
}
