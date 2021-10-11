package com.zara.price.fixture;

import com.zara.price.enums.Brand;
import com.zara.price.enums.Currency;
import com.zara.price.repository.model.Price;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import static com.zara.price.enums.Brand.ZARA;

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

    public static com.zara.price.controller.dto.Price price_useCase1() {
        return price(ZARA,
                35455L,
                1L,
                ZonedDateTime.parse("2020-06-14T00:00-03:00"),
                ZonedDateTime.parse("2020-12-31T23:59:59-03:00"),
                new BigDecimal("35.50"));
    }

    public static com.zara.price.controller.dto.Price price_useCase6() {
        return price(ZARA,
                35455L,
                4L,
                ZonedDateTime.parse("2021-01-10T10:00-03:00"),
                ZonedDateTime.parse("2021-01-10T16:00-03:00"),
                new BigDecimal("30.95"));
    }

    public static com.zara.price.controller.dto.Price price_useCase7() {
        return price(ZARA,
                35455L,
                4L,
                ZonedDateTime.parse("2021-01-10T16:00-03:00"),
                ZonedDateTime.parse("2021-01-10T23:59:59-03:00"),
                new BigDecimal("45.75"));
    }

    public static com.zara.price.controller.dto.Price price(Brand brand, Long productId, Long priceList, ZonedDateTime start, ZonedDateTime end, BigDecimal price) {
        return com.zara.price.controller.dto.Price.builder()
                .productId(productId)
                .brand(brand)
                .priceList(priceList)
                .startDate(start)
                .endDate(end)
                .price(price)
                .currency(Currency.EUR)
                .build();
    }
}
