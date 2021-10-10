package com.zara.price.exception;

public class InvalidProductException extends PriceException {

    private static final String MESSAGE = "Invalid product.";

    public InvalidProductException() {
        super(MESSAGE);
    }
}
