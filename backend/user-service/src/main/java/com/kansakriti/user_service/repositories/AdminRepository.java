package com.kansakriti.user_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kansakriti.user_service.model.Admin;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);
}
