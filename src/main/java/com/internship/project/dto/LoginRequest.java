package com.internship.project.dto;

import jakarta.validation.constraints.NotBlank;

// Yêu cầu đăng nhập
public record LoginRequest(
    @NotBlank String username,
    @NotBlank String password
) {
}
