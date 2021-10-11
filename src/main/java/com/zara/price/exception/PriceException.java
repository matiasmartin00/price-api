package com.zara.price.exception;

public abstract class PriceException extends RuntimeException {

  public PriceException(String message) {
    super(message);
  }
}
