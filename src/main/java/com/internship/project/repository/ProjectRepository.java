package com.internship.project.repository;

import com.internship.project.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository dự án
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
}
