package com.zara.price.controller;

import com.zara.price.controller.dto.Price;
import com.zara.price.enums.Brand;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.ZoneId;
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
        return ResponseEntity.ok(
                Price.builder()
                        .productId(productId)
                        .brand(brand)
                        .priceList(1L)
                        .startDate(ZonedDateTime.of(2021, 1, 1, 17, 20, 0, 0, ZoneId.of("America/Argentina/Buenos_Aires")))
                        .endDate(ZonedDateTime.of(2021, 1, 29, 23, 59, 0, 0, ZoneId.of("America/Argentina/Buenos_Aires")))
                        .price(new BigDecimal("10.6573"))
                        .build()
        );
    }
}
