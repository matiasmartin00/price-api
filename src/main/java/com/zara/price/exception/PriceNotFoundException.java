package com.zara.price.exception;

public class PriceNotFoundException extends PriceException {

  private static final String MESSAGE = "Price not found";

  public PriceNotFoundException() {
    super(MESSAGE);
  }
}
