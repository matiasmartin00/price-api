package com.zara.price.service;

import com.zara.price.exception.InvalidBrandException;
import com.zara.price.exception.InvalidDateException;
import com.zara.price.exception.InvalidProductException;
import com.zara.price.exception.PriceNotFoundException;
import com.zara.price.fixture.PriceFixture;
import com.zara.price.repository.PriceRepository;
import com.zara.price.serivce.PriceService;
import com.zara.price.serivce.PriceServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.ZonedDateTime;
import java.util.List;

import static com.zara.price.enums.Brand.ZARA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({SpringExtension.class})
public class PriceServiceTest {

    @TestConfiguration
    static class TestContextConfiguration {
        @Bean
        public PriceServiceImpl priceService(PriceRepository priceRepository) {
            return new PriceServiceImpl(priceRepository);
        }
    }

    @MockBean
    private PriceRepository priceRepository;

    @Autowired
    private PriceService priceService;

    @Test
    public void getPrice() {
        var brand = ZARA;
        var productId = 1L;
        var date = ZonedDateTime.parse("2021-01-01T00:00:00-03:00");

        var prices = PriceFixture.prices();

        when(priceRepository.findAllByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityAsc(eq(brand.getId()), eq(productId), eq(date), eq(date)))
                .thenReturn(prices);

        var price = priceService.getPrice(brand, productId, date);

        assertThat(price)
                .isEqualTo(prices.stream().findFirst().get());

        verify(priceRepository).findAllByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityAsc(eq(brand.getId()), eq(productId), eq(date), eq(date));
    }

    @Test
    public void getPrice_not_found() {
        var brand = ZARA;
        var productId = 1L;
        var date = ZonedDateTime.parse("2021-01-01T00:00:00-03:00");

        when(priceRepository.findAllByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityAsc(eq(brand.getId()), eq(productId), eq(date), eq(date)))
                .thenReturn(List.of());

        assertThatThrownBy(() -> priceService.getPrice(brand, productId, date))
                .isInstanceOf(PriceNotFoundException.class)
                .hasMessage("Price not found");

        verify(priceRepository).findAllByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityAsc(eq(brand.getId()), eq(productId), eq(date), eq(date));
    }

    @ParameterizedTest
    @ValueSource(longs = {0L, -1L})
    public void getPrice_invalid_product_id(Long productId) {
        var date = ZonedDateTime.parse("2021-01-01T00:00:00-03:00");

        assertThatThrownBy(() -> priceService.getPrice(ZARA, productId, date))
                .isInstanceOf(InvalidProductException.class)
                .hasMessage("Invalid product.");

        verify(priceRepository, never()).findAllByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityAsc(any(), any(), any(), any());
    }

    @Test
    public void getPrice_invalid_brand() {
        var productId = 1L;
        var date = ZonedDateTime.parse("2021-01-01T00:00:00-03:00");

        assertThatThrownBy(() -> priceService.getPrice(null, productId, date))
                .isInstanceOf(InvalidBrandException.class)
                .hasMessage("Invalid brand.");

        verify(priceRepository, never()).findAllByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityAsc(any(), any(), any(), any());
    }

    @Test
    public void getPrice_invalid_date() {
        var brand = ZARA;
        var productId = 1L;

        assertThatThrownBy(() -> priceService.getPrice(brand, productId, null))
                .isInstanceOf(InvalidDateException.class)
                .hasMessage("Invalid date.");

        verify(priceRepository, never()).findAllByBrandIdAndProductIdAndStartDateBeforeAndEndDateAfterOrderByPriorityAsc(any(), any(), any(), any());
    }
}
