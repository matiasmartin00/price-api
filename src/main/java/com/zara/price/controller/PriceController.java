package com.zara.price.controller;

import com.zara.price.controller.dto.Price;
import com.zara.price.enums.Brand;
import com.zara.price.exception.InvalidProductException;
import com.zara.price.serivce.PriceService;
import com.zara.price.transformer.PriceTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/prices")
public class PriceController {

    private final PriceService priceService;
    private final PriceTransformer priceTransformer;

    @GetMapping
    public ResponseEntity<Price> getPrice(
            @RequestParam Brand brand,
            @RequestParam("product_id") Long productId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime date
            ) {
        checkProductId(productId);
        return ResponseEntity.ok(priceTransformer.to(priceService.getPrice(brand, productId, date)));
    }
    private void checkProductId(Long productId) {
        if (productId <= 0) {
            throw new InvalidProductException();
        }
    }
}
