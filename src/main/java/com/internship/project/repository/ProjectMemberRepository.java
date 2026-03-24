package com.internship.project.repository;

import com.internship.project.entity.ProjectMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository thành viên dự án
public interface ProjectMemberRepository extends JpaRepository<ProjectMemberEntity, Long> {
    // Kiểm tra thành viên
    boolean existsByProjectIdAndUserIdAndActiveTrue(Long projectId, Long userId);
}
