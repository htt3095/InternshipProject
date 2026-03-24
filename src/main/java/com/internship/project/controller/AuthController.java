package com.internship.project.controller;

import com.internship.project.dto.ApiResponse;
import com.internship.project.dto.AuthResponse;
import com.internship.project.dto.LoginRequest;
import com.internship.project.dto.RegisterRequest;
import com.internship.project.entity.UserEntity;
import com.internship.project.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Controller xác thực
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Đăng ký tài khoản
    @PostMapping("/register")
    public ApiResponse<UserEntity> register(@Valid @RequestBody RegisterRequest request) {
        return new ApiResponse<>(201, "Đăng ký thành công", authService.register(request));
    }

    // Đăng nhập hệ thống
    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return new ApiResponse<>(200, "Đăng nhập thành công", authService.login(request));
    }
}
