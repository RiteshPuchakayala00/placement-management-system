package com.pms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>>
    handleResourceAlreadyExistsException(
            ResourceAlreadyExistsException ex
    ) {

        Map<String, String> response = new HashMap<>();

        response.put("error", ex.getMessage());

        return new ResponseEntity<>(
                response,
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>>
    handleValidationException(
            MethodArgumentNotValidException ex
    ) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        ));

        return new ResponseEntity<>(
                errors,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String , String>>
    handleResourceNotFoundException(
            ResourceNotFoundException ex
    ) {
        Map <String , String> response = new HashMap<>();

        response.put("error", ex.getMessage());

        return new ResponseEntity<>(
                response ,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, String>>
    handleInvalidCredentialsException(
            InvalidCredentialsException ex
    ) {

        Map<String, String> response = new HashMap<>();

        response.put("error", ex.getMessage());

        return new ResponseEntity<>(
                response,
                HttpStatus.UNAUTHORIZED
        );

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleBusinessLogicExceptions(RuntimeException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());

        // 400 Bad Request is perfect for business rule violations
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    // Catches Security Role Violations (e.g. Student trying to access Company endpoint)
    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    public ResponseEntity<Map<String, String>> handleAccessDeniedException(org.springframework.security.access.AccessDeniedException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Access Denied: You do not have the required permissions to perform this action.");

        // 403 Forbidden is the standard for role violations
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
    // The Ultimate Fallback: Catches actual server crashes (NullPointers, Database down, etc.)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGlobalExceptions(Exception ex) {
        Map<String, String> response = new HashMap<>();
        // In a real production app, you might not want to expose the raw message here for security,
        // but it's great for debugging!
        response.put("error", "An unexpected error occurred: " + ex.getMessage());

        // 500 Internal Server Error
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
