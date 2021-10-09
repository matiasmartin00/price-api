package com.zara.price.controller;

import com.zara.price.controller.dto.Price;
import com.zara.price.enums.Brand;
import com.zara.price.exception.InvalidProductException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@RestController
@RequestMapping("/v1/prices")
public class PriceController {

    @GetMapping
    public ResponseEntity<Price> getPrice(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime date,
            @RequestParam("product_id") Long productId,
            @RequestParam Brand brand
            ) {
        checkProductId(productId);
        return ResponseEntity.ok(
                Price.builder()
                        .productId(productId)
                        .brand(brand)
                        .priceList(1L)
                        .startDate(ZonedDateTime.parse("2021-01-01T00:00:00-03:00"))
                        .endDate(ZonedDateTime.parse("2021-01-02T00:00:00-03:00"))
                        .price(new BigDecimal("10.6573"))
                        .build()
        );
    }

    private void checkProductId(Long productId) {
        if (productId <= 0) {
            throw new InvalidProductException();
        }
    }
}
