package com.internship.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Yêu cầu tạo dự án
public record ProjectRequest(
    @NotBlank @Size(max = 150) String name,
    @Size(max = 500) String description
) {
}
