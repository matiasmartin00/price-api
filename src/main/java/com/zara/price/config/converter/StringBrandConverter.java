package com.zara.price.config.converter;

import com.zara.price.enums.Brand;
import com.zara.price.exception.InvalidBrandException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

public class StringBrandConverter implements Converter<String, Brand> {
    @Override
    public Brand convert(String s) {
        if (StringUtils.isBlank(s)) {
            throw new InvalidBrandException();
        }
        return Brand.getBrand(s);
    }
}
