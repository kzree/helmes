package com.kzree.backend.common.errors;

import java.io.Serial;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String errorCode;

    public ResourceNotFoundException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ResourceNotFoundException(String message) {
        this(message, ErrorCodes.RESOURCE_NOT_FOUND);
    }
}
