package com.internship.project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Yêu cầu cập nhật dự án
public record UpdateProjectRequest(
    @NotBlank @Size(max = 150) String name,
    @Size(max = 500) String description
) {
}
