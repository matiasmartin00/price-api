package com.zara.price.serivce;

import com.zara.price.enums.Brand;
import com.zara.price.repository.model.Price;

import java.time.ZonedDateTime;

public interface PriceService {

    Price getPrice(Brand brand, Long productId, ZonedDateTime date);
}
