package com.zara.price.serivce;

import static java.util.Objects.isNull;

import com.zara.price.enums.Brand;
import com.zara.price.exception.InvalidBrandException;
import com.zara.price.exception.InvalidDateException;
import com.zara.price.exception.InvalidProductException;
import com.zara.price.exception.PriceNotFoundException;
import com.zara.price.repository.PriceRepository;
import com.zara.price.repository.model.Price;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

  private final PriceRepository priceRepository;

  public Price getPrice(Brand brand, Long productId, ZonedDateTime date) {
    checkBrand(brand);
    checkProductId(productId);
    checkDate(date);
    return getPrices(brand, productId, date)
        .stream()
        .findFirst()
        .orElseThrow(PriceNotFoundException::new);
  }

  private void checkDate(ZonedDateTime date) {
    if (isNull(date)) {
      throw new InvalidDateException();
    }
  }

  private void checkProductId(Long productId) {
    if (isNull(productId) || productId <= 0) {
      throw new InvalidProductException();
    }
  }

  private void checkBrand(Brand brand) {
    if (isNull(brand)) {
      throw new InvalidBrandException();
    }
  }

  private List<Price> getPrices(Brand brand, Long productId, ZonedDateTime date) {
    return priceRepository
        .findAllByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityAsc(
            brand.getId(), productId, date, date);
  }
}
