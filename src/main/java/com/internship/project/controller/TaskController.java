package com.internship.project.controller;

import com.internship.project.dto.ApiResponse;
import com.internship.project.dto.AssignTaskRequest;
import com.internship.project.dto.TaskCreateRequest;
import com.internship.project.dto.UpdateStatusRequest;
import com.internship.project.dto.UpdateTaskRequest;
import com.internship.project.entity.TaskEntity;
import com.internship.project.exception.CustomException;
import com.internship.project.service.TaskService;
import com.internship.project.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Controller công việc
@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Tasks", description = "Task management")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    // Tạo công việc
    @Operation(summary = "Create a new task (MANAGER only)")
    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping
    public ApiResponse<TaskEntity> create(@Valid @RequestBody TaskCreateRequest request) {
        return new ApiResponse<>(201, "Tạo công việc thành công", taskService.create(request));
    }

    // Lấy tất cả
    @Operation(summary = "Get all tasks (MANAGER only)")
    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping
    public ApiResponse<List<TaskEntity>> listAll() {
        return new ApiResponse<>(200, "Lấy danh sách công việc thành công", taskService.findAll());
    }

    // Lấy chi tiết
    @Operation(summary = "Get task by ID (USER sees own tasks only)")
    @GetMapping("/{taskId}")
    public ApiResponse<TaskEntity> getTask(@PathVariable Long taskId, Authentication authentication) {
        TaskEntity task = taskService.findById(taskId);
        boolean isManager = authentication.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals("ROLE_MANAGER"));
        if (!isManager && (task.getAssignee() == null || !task.getAssignee().getUsername().equals(authentication.getName()))) {
            throw new CustomException(HttpStatus.FORBIDDEN, "Người dùng chỉ được xem công việc của mình");
        }
        return new ApiResponse<>(200, "Lấy thông tin công việc thành công", task);
    }

    // Cập nhật công việc
    @Operation(summary = "Update task (MANAGER only)")
    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("/{taskId}")
    public ApiResponse<TaskEntity> updateTask(@PathVariable Long taskId, @Valid @RequestBody UpdateTaskRequest request) {
        return new ApiResponse<>(200, "Cập nhật công việc thành công", taskService.update(taskId, request));
    }

    // Xóa công việc
    @Operation(summary = "Delete task (MANAGER only)")
    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/{taskId}")
    public ApiResponse<String> deleteTask(@PathVariable Long taskId) {
        taskService.delete(taskId);
        return new ApiResponse<>(200, "Xóa công việc thành công", "OK");
    }

    // Giao công việc
    @Operation(summary = "Assign task to a user (MANAGER only)")
    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("/{taskId}/assign")
    public ApiResponse<TaskEntity> assignTask(@PathVariable Long taskId, @Valid @RequestBody AssignTaskRequest request) {
        return new ApiResponse<>(200, "Giao công việc thành công", taskService.assignTask(taskId, request.userId()));
    }

    // Cập nhật trạng thái
    @Operation(summary = "Update task status (USER updates own tasks only)")
    @PutMapping("/{taskId}/status")
    public ApiResponse<TaskEntity> updateStatus(@PathVariable Long taskId,
                                                @Valid @RequestBody UpdateStatusRequest request,
                                                Authentication authentication) {
        TaskEntity task = taskService.findById(taskId);
        boolean isUser = authentication.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals("ROLE_USER"));
        if (task.getAssignee() != null && isUser
            && !task.getAssignee().getUsername().equals(authentication.getName())) {
            throw new CustomException(HttpStatus.FORBIDDEN, "Người dùng chỉ được cập nhật công việc của mình");
        }
        return new ApiResponse<>(200, "Cập nhật trạng thái thành công", taskService.updateStatus(taskId, request.status()));
    }

    // Lấy theo dự án
    @Operation(summary = "Get tasks by project")
    @GetMapping("/by-project/{projectId}")
    public ApiResponse<List<TaskEntity>> listByProject(@PathVariable Long projectId) {
        return new ApiResponse<>(200, "Lấy danh sách công việc thành công", taskService.findByProject(projectId));
    }

    // Lấy theo người
    @Operation(summary = "Get tasks by user (USER sees own tasks only)")
    @GetMapping("/by-user/{userId}")
    public ApiResponse<List<TaskEntity>> listByUser(@PathVariable Long userId, Authentication authentication) {
        boolean isManager = authentication.getAuthorities().stream()
            .anyMatch(a -> a.getAuthority().equals("ROLE_MANAGER"));
        String requestedUsername = userService.findById(userId).getUsername();
        if (!isManager && !authentication.getName().equals(requestedUsername)) {
            throw new CustomException(HttpStatus.FORBIDDEN, "Người dùng chỉ được xem công việc của mình");
        }
        return new ApiResponse<>(200, "Lấy danh sách công việc thành công", taskService.findByUser(userId));
    }

    // Lấy theo trạng thái
    @Operation(summary = "Get tasks by status")
    @GetMapping("/by-status")
    public ApiResponse<List<TaskEntity>> listByStatus(@RequestParam String status) {
        return new ApiResponse<>(200, "Lấy danh sách công việc thành công", taskService.findByStatus(status));
    }
}
