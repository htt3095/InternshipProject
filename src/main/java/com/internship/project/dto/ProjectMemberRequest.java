package com.internship.project.dto;

import jakarta.validation.constraints.NotNull;

// Yêu cầu thêm thành viên
public record ProjectMemberRequest(@NotNull Long userId) {
}
