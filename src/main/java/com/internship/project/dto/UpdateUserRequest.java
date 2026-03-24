package com.internship.project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Yêu cầu cập nhật người dùng
public record UpdateUserRequest(
    @NotBlank @Size(min = 3, max = 100) String username,
    @NotBlank @Email String email
) {
}
