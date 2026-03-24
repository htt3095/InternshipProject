package com.internship.project.exception;

import org.springframework.http.HttpStatus;

// Lỗi tùy chỉnh
public class CustomException extends RuntimeException {

    private final HttpStatus status;

    public CustomException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
