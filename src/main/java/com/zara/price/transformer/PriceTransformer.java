package com.zara.price.transformer;

import com.zara.price.controller.dto.Price;

public interface PriceTransformer {

  Price to(com.zara.price.repository.model.Price price);
}
