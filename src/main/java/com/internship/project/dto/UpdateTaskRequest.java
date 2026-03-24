package com.internship.project.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

// Yêu cầu cập nhật công việc
public record UpdateTaskRequest(
    @NotBlank @Size(max = 200) String title,
    @Size(max = 1000) String description,
    @NotNull @Future LocalDate deadline,
    String status
) {
}
