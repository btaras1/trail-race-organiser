package com.intellexi.race_command_service.exception;

public class ApplicationBadRequestException extends RuntimeException {
    public ApplicationBadRequestException(String message) {
        super(message);
    }
}
