package com.zara.price.transformer;

import com.zara.price.controller.dto.Price;
import com.zara.price.enums.Brand;
import org.springframework.stereotype.Component;

@Component
public class PriceTransformerImpl implements PriceTransformer {

    public Price to(com.zara.price.repository.model.Price price) {
        return Price.builder()
                .priceList(price.getPriceList())
                .endDate(price.getEndDate())
                .brand(Brand.getBrand(price.getBrandId()))
                .productId(price.getProductId())
                .startDate(price.getStartDate())
                .productId(price.getProductId())
                .price(price.getPrice())
                .currency(price.getCurr())
                .build();
    }
}
