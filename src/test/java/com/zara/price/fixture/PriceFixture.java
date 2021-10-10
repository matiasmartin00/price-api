package com.zara.price.fixture;

import com.zara.price.enums.Currency;
import com.zara.price.repository.model.Price;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

public class PriceFixture {

    public static List<Price> prices() {
        return List.of(
                price()
        );
    }

    public static Price price() {
        return Price.builder()
                .productId(1L)
                .brandId(1L)
                .priceList(1L)
                .startDate(ZonedDateTime.parse("2021-01-01T00:00:00-03:00"))
                .endDate(ZonedDateTime.parse("2021-01-02T00:00:00-03:00"))
                .price(new BigDecimal("10.6573"))
                .curr(Currency.EUR)
                .priority(0)
                .build();
    }
}
