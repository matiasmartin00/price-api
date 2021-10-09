package com.zara.price.exception;

public class InvalidProductException extends BadRequestException {

    private static final String MESSAGE = "Invalid product.";

    public InvalidProductException() {
        super(MESSAGE);
    }
}
