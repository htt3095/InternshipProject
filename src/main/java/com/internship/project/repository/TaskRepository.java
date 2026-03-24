package com.internship.project.repository;

import com.internship.project.entity.TaskEntity;
import com.internship.project.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Repository công việc
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    // Theo dự án
    List<TaskEntity> findByProjectId(Long projectId);

    // Theo người nhận
    List<TaskEntity> findByAssigneeId(Long assigneeId);

    // Theo trạng thái
    List<TaskEntity> findByStatus(TaskStatus status);
}
