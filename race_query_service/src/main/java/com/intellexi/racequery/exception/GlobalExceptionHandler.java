package com.intellexi.racequery.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ApplicationNotFoundException.class, RaceNotFoundException.class, UserNotFoundException.class})
    public ResponseEntity<Object> handleStudentNotFoundException(RuntimeException exception) {
        return ResponseEntity
                .status(NOT_FOUND)
                .body(constructResponse(NOT_FOUND.value(), exception.getMessage()));
    }
    @ExceptionHandler({EventTypeUnknownException.class})
    public ResponseEntity<Object> handleStudentAlreadyExistsException(EventTypeUnknownException exception) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(constructResponse(BAD_REQUEST.value(), exception.getMessage()));
    }
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleRuntimeException(AccessDeniedException exception) {
        return ResponseEntity
                .status(UNAUTHORIZED)
                .body(constructResponse(UNAUTHORIZED.value(), exception.getMessage()));
    }

    private ProblemDto constructResponse(Integer code, String message) {
        return ProblemDto.builder()
                .code(code)
                .message(message)
                .build();
    }
}
