package com.kzree.backend.common.errors;

import java.io.Serial;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String errorCode;

    public BadRequestException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BadRequestException(String message) {
        super(message);
        this.errorCode = ErrorCodes.INVALID_REQUEST;
    }
}
