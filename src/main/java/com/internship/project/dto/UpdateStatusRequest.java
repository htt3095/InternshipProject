package com.internship.project.dto;

import jakarta.validation.constraints.NotBlank;

// Yêu cầu cập nhật trạng thái
public record UpdateStatusRequest(@NotBlank String status) {
}
