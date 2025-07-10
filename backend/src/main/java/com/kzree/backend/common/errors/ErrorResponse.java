package com.kzree.backend.common.errors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private String error;
    private String message;
    private String code;
    private int status;
    private String path;
}
