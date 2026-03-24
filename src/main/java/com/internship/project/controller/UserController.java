package com.internship.project.controller;

import com.internship.project.dto.ApiResponse;
import com.internship.project.dto.CreateUserRequest;
import com.internship.project.dto.UpdateUserRequest;
import com.internship.project.entity.UserEntity;
import com.internship.project.service.UserService;
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

// Controller người dùng
@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('MANAGER')")
@Tag(name = "Users", description = "User management (MANAGER only)")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Tạo người dùng
    @Operation(summary = "Create a new user")
    @PostMapping
    public ApiResponse<UserEntity> createUser(@Valid @RequestBody CreateUserRequest request) {
        return new ApiResponse<>(201, "Tạo người dùng thành công", userService.create(request));
    }

    // Lấy tất cả
    @Operation(summary = "Get all users")
    @GetMapping
    public ApiResponse<List<UserEntity>> listUsers() {
        return new ApiResponse<>(200, "Lấy danh sách người dùng thành công", userService.findAll());
    }

    // Lấy chi tiết
    @Operation(summary = "Get user by ID")
    @GetMapping("/{userId}")
    public ApiResponse<UserEntity> getUser(@PathVariable Long userId) {
        return new ApiResponse<>(200, "Lấy thông tin người dùng thành công", userService.findById(userId));
    }

    // Cập nhật người dùng
    @Operation(summary = "Update user")
    @PutMapping("/{userId}")
    public ApiResponse<UserEntity> updateUser(@PathVariable Long userId, @Valid @RequestBody UpdateUserRequest request) {
        return new ApiResponse<>(200, "Cập nhật người dùng thành công", userService.update(userId, request));
    }

    // Xóa người dùng
    @Operation(summary = "Delete user")
    @DeleteMapping("/{userId}")
    public ApiResponse<String> deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
        return new ApiResponse<>(200, "Xóa người dùng thành công", "OK");
    }
}
