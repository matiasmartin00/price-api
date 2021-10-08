package com.zara.price.enums;

import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public enum Brand {

    DEFAULT(-1L),
    ZARA(1L);

    private Long id;

    public static Brand getBrand(String brand) {
        return Stream.of(values())
                .filter(b -> b.name().equalsIgnoreCase(brand))
                .findFirst()
                .orElse(DEFAULT);
    }
}
