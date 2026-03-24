package com.internship.project.dto;

import jakarta.validation.constraints.NotNull;

// Yêu cầu giao việc
public record AssignTaskRequest(@NotNull Long userId) {
}
