package com.internship.project.service;

import com.internship.project.dto.TaskCreateRequest;
import com.internship.project.dto.UpdateTaskRequest;
import com.internship.project.entity.ProjectEntity;
import com.internship.project.entity.TaskEntity;
import com.internship.project.entity.UserEntity;
import com.internship.project.enums.TaskStatus;
import com.internship.project.exception.CustomException;
import com.internship.project.repository.ProjectMemberRepository;
import com.internship.project.repository.ProjectRepository;
import com.internship.project.repository.TaskRepository;
import com.internship.project.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

// Service công việc
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMemberRepository projectMemberRepository;

    public TaskService(TaskRepository taskRepository,
                       ProjectRepository projectRepository,
                       UserRepository userRepository,
                       ProjectMemberRepository projectMemberRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.projectMemberRepository = projectMemberRepository;
    }

    // Tạo công việc
    public TaskEntity create(TaskCreateRequest request) {
        ProjectEntity project = projectRepository.findById(request.projectId())
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Dự án không tồn tại"));

        TaskEntity task = new TaskEntity();
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setDeadline(request.deadline());
        task.setStatus(TaskStatus.TODO);
        task.setProject(project);
        return taskRepository.save(task);
    }

    // Giao công việc
    public TaskEntity assignTask(Long taskId, Long userId) {
        TaskEntity task = findById(taskId);
        UserEntity user = userRepository.findById(userId)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Người dùng không tồn tại"));

        if (!projectMemberRepository.existsByProjectIdAndUserIdAndActiveTrue(task.getProject().getId(), userId)) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Người dùng không phải thành viên dự án");
        }

        task.setAssignee(user);
        return taskRepository.save(task);
    }

    // Cập nhật trạng thái
    public TaskEntity updateStatus(Long taskId, String nextStatus) {
        TaskEntity task = findById(taskId);
        if (task.getStatus() == TaskStatus.DONE) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Công việc đã hoàn thành, không thể cập nhật");
        }

        TaskStatus status;
        try {
            status = TaskStatus.valueOf(nextStatus.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Trạng thái không hợp lệ: " + nextStatus);
        }

        task.setStatus(status);
        return taskRepository.save(task);
    }

    // Tìm theo ID
    public TaskEntity findById(Long taskId) {
        return taskRepository.findById(taskId)
            .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "Công việc không tồn tại"));
    }

    // Lấy theo dự án
    public List<TaskEntity> findByProject(Long projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    // Lấy theo người
    public List<TaskEntity> findByUser(Long userId) {
        return taskRepository.findByAssigneeId(userId);
    }

    // Lấy theo trạng thái
    public List<TaskEntity> findByStatus(String status) {
        try {
            return taskRepository.findByStatus(TaskStatus.valueOf(status.toUpperCase()));
        } catch (IllegalArgumentException ex) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "Trạng thái không hợp lệ: " + status);
        }
    }

    // Lấy tất cả
    public List<TaskEntity> findAll() {
        return taskRepository.findAll();
    }

    // Cập nhật công việc
    public TaskEntity update(Long id, UpdateTaskRequest request) {
        TaskEntity task = findById(id);
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setDeadline(request.deadline());
        if (request.status() != null) {
            try {
                task.setStatus(TaskStatus.valueOf(request.status().toUpperCase()));
            } catch (IllegalArgumentException ex) {
                throw new CustomException(HttpStatus.BAD_REQUEST, "Trạng thái không hợp lệ: " + request.status());
            }
        }
        return taskRepository.save(task);
    }

    // Xóa công việc
    public void delete(Long id) {
        findById(id);
        taskRepository.deleteById(id);
    }
}
