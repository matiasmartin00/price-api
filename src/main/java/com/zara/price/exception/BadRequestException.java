package com.zara.price.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ApiException {

    private static final String CODE = "bad_request";

    public BadRequestException(String message) {
        super(CODE, HttpStatus.BAD_REQUEST, message);
    }
}
