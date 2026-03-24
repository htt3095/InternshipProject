package com.internship.project.dto;

// Phản hồi xác thực
public record AuthResponse(String token, String tokenType, String username) {
}
