package com.kzree.backend.common.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final String RESOURCE_NOT_FOUND = "Resource not found";
    private final String BAD_REQUEST = "Bad request";
    private final String INTERNAL_SERVER_ERROR = "Internal server error";

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest req) {
        ErrorResponse errorResponse = new ErrorResponse(RESOURCE_NOT_FOUND, ex.getMessage(),
                ex.getErrorCode(),
                HttpStatus.NOT_FOUND.value(),
                req.getDescription(false).replace("uri=", ""));

        return ResponseEntity.status(404).body(errorResponse);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoHandlerFoundException(NoHandlerFoundException ex, WebRequest req) {
        ErrorResponse errorResponse = new ErrorResponse(RESOURCE_NOT_FOUND, ex.getMessage(),
                ErrorCodes.RESOURCE_NOT_FOUND,
                HttpStatus.NOT_FOUND.value(),
                req.getDescription(false).replace("uri=", ""));

        return ResponseEntity.status(404).body(errorResponse);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex, WebRequest req) {
        ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST, ex.getMessage(),
                ex.getErrorCode(),
                HttpStatus.BAD_REQUEST.value(),
                req.getDescription(false).replace("uri=", ""));

        return ResponseEntity.status(400).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException ex, WebRequest request) {

        StringBuilder message = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(
                error -> message.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; "));

        ErrorResponse errorResponse = new ErrorResponse(
                BAD_REQUEST,
                message.toString(),
                ErrorCodes.VALIDATION_ERROR,
                HttpStatus.BAD_REQUEST.value(),
                request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex, WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(
                INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                ErrorCodes.INTERNAL_ERROR,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                request.getDescription(false).replace("uri=", ""));

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
