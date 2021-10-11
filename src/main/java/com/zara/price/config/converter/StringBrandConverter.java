package com.zara.price.config.converter;

import com.zara.price.enums.Brand;
import com.zara.price.exception.InvalidBrandException;
import org.springframework.core.convert.converter.Converter;

public class StringBrandConverter implements Converter<String, Brand> {

  @Override
  public Brand convert(String s) {
    try {
      return Brand.getBrand(s);
    } catch (InvalidBrandException ex) {
      return null;
    }
  }
}
