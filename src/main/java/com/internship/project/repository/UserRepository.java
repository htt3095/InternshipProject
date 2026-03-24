package com.internship.project.repository;

import com.internship.project.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Repository người dùng
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // Tìm theo tên
    Optional<UserEntity> findByUsername(String username);

    // Kiểm tra tên
    boolean existsByUsername(String username);

    // Kiểm tra email
    boolean existsByEmail(String email);
}
