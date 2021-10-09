package com.zara.price.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public abstract class ApiException extends RuntimeException {

    private String code;
    private HttpStatus status;

    public ApiException(String code, HttpStatus status, String message) {
        super(message);
        this.code = code;
        this.status = status;
    }
}
