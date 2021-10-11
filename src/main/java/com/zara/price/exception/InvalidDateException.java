package com.zara.price.exception;

public class InvalidDateException extends PriceException {

  private static final String MESSAGE = "Invalid date.";

  public InvalidDateException() {
    super(MESSAGE);
  }
}
