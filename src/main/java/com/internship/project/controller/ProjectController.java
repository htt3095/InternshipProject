package com.internship.project.controller;

import com.internship.project.dto.ApiResponse;
import com.internship.project.dto.ProjectMemberRequest;
import com.internship.project.dto.ProjectRequest;
import com.internship.project.dto.UpdateProjectRequest;
import com.internship.project.entity.ProjectEntity;
import com.internship.project.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Controller dự án
@RestController
@RequestMapping("/api/projects")
@Tag(name = "Projects", description = "Project management")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    // Tạo dự án
    @Operation(summary = "Create a new project (MANAGER only)")
    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping
    public ApiResponse<ProjectEntity> create(@Valid @RequestBody ProjectRequest request) {
        return new ApiResponse<>(201, "Tạo dự án thành công", projectService.create(request));
    }

    // Lấy tất cả
    @Operation(summary = "Get all projects")
    @GetMapping
    public ApiResponse<List<ProjectEntity>> list() {
        return new ApiResponse<>(200, "Lấy danh sách dự án thành công", projectService.findAll());
    }

    // Lấy chi tiết
    @Operation(summary = "Get project by ID")
    @GetMapping("/{projectId}")
    public ApiResponse<ProjectEntity> getProject(@PathVariable Long projectId) {
        return new ApiResponse<>(200, "Lấy thông tin dự án thành công", projectService.findById(projectId));
    }

    // Cập nhật dự án
    @Operation(summary = "Update project (MANAGER only)")
    @PreAuthorize("hasRole('MANAGER')")
    @PutMapping("/{projectId}")
    public ApiResponse<ProjectEntity> update(@PathVariable Long projectId, @Valid @RequestBody UpdateProjectRequest request) {
        return new ApiResponse<>(200, "Cập nhật dự án thành công", projectService.update(projectId, request));
    }

    // Xóa dự án
    @Operation(summary = "Delete project (MANAGER only)")
    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/{projectId}")
    public ApiResponse<String> delete(@PathVariable Long projectId) {
        projectService.delete(projectId);
        return new ApiResponse<>(200, "Xóa dự án thành công", "OK");
    }

    // Thêm thành viên
    @Operation(summary = "Add member to project (MANAGER only)")
    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/{projectId}/members")
    public ApiResponse<String> addMember(@PathVariable Long projectId, @Valid @RequestBody ProjectMemberRequest request) {
        projectService.addMember(projectId, request.userId());
        return new ApiResponse<>(200, "Thêm thành viên thành công", "OK");
    }
}
