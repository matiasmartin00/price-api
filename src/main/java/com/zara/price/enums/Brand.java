package com.zara.price.enums;

import com.zara.price.exception.InvalidBrandException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum Brand {

    ZARA(1L);

    private Long id;

    public static Brand getBrand(String brand) {
        return Stream.of(values())
                .filter(b -> b.name().equalsIgnoreCase(brand))
                .findFirst()
                .orElseThrow(InvalidBrandException::new);
    }

    public static Brand getBrand(Long id) {
        return Stream.of(values())
                .filter(b -> b.id.equals(id))
                .findFirst()
                .orElseThrow(InvalidBrandException::new);
    }
}
