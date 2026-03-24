package com.internship.project.repository;

import com.internship.project.entity.RoleEntity;
import com.internship.project.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Repository vai trò
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    // Tìm theo tên
    Optional<RoleEntity> findByName(RoleName name);
}
