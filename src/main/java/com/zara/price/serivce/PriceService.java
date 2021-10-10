package com.zara.price.serivce;

import com.zara.price.enums.Brand;
import com.zara.price.exception.PriceNotFoundException;
import com.zara.price.repository.PriceRepository;
import com.zara.price.repository.model.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final PriceRepository priceRepository;

    public Price getPrice(Brand brand, Long productId, ZonedDateTime date) {
        return getPrices(brand, productId, date)
                .stream()
                .findFirst()
                .orElseThrow(PriceNotFoundException::new);
    }

    private List<Price> getPrices(Brand brand, Long productId, ZonedDateTime date) {
        return priceRepository.findAllByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityAsc(brand.getId(), productId, date, date);
    }
}
