package com.student.exception;

import org.springframework.http.HttpStatus;

public class StudentApiException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public StudentApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
