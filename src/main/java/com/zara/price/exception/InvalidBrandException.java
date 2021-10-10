package com.zara.price.exception;

public class InvalidBrandException extends PriceException {

    private static final String MESSAGE = "Invalid brand.";

    public InvalidBrandException() {
        super(MESSAGE);
    }
}
