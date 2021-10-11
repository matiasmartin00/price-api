package com.zara.price.repository;

import com.zara.price.repository.model.Price;
import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

  List<Price> findAllByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityAsc(
      Long brandId, Long productId, ZonedDateTime startDate, ZonedDateTime endDate);
}
