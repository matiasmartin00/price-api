package com.zara.price.controller;

import com.zara.price.controller.dto.ApiError;
import com.zara.price.controller.dto.Price;
import com.zara.price.fixture.ApiErrorFixture;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static com.zara.price.enums.Brand.ZARA;
import static org.assertj.core.api.Assertions.assertThat;

public class PriceControllerTest extends ControllerTest {

    @Test
    public void getPrice() {
        Price expected = Price.builder()
                .productId(1L)
                .brand(ZARA)
                .priceList(1L)
                .startDate(ZonedDateTime.parse("2021-01-01T00:00:00-03:00"))
                .endDate(ZonedDateTime.parse("2021-01-02T00:00:00-03:00"))
                .price(new BigDecimal("10.6573"))
                .build();

        ResponseEntity<Price> responseEntity = this.testRestTemplate.exchange("/v1/prices?date=2021-01-01T17:20:00.000-03:00&product_id=1&brand=ZARA", HttpMethod.GET, this.getDefaultRequestEntity(), Price.class);

        assertThat(responseEntity.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody())
                .isEqualTo(expected);
    }

    @Test
    public void getPrice_missingDate() {
        ApiError expected = ApiErrorFixture.missingParameter("date");
        ResponseEntity<ApiError> responseEntity = this.testRestTemplate.exchange("/v1/prices?product_id=1&brand=ZARA", HttpMethod.GET, this.getDefaultRequestEntity(), ApiError.class);

        assertThat(responseEntity.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody())
                .isEqualTo(expected);

    }

    @Test
    public void getPrice_missingProductId() {
        ApiError expected = ApiErrorFixture.missingParameter("product_id");
        ResponseEntity<ApiError> responseEntity = this.testRestTemplate.exchange("/v1/prices?date=2021-01-01T17:20:00.000-03:00&brand=ZARA", HttpMethod.GET, this.getDefaultRequestEntity(), ApiError.class);

        assertThat(responseEntity.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody())
                .isEqualTo(expected);
    }

    @Test
    public void getPrice_invalidProductId() {
        ApiError expected = ApiErrorFixture.badRequest("Invalid product.");
        ResponseEntity<ApiError> responseEntity = this.testRestTemplate.exchange("/v1/prices?date=2021-01-01T17:20:00.000-03:00&product_id=-1&brand=ZARA", HttpMethod.GET, this.getDefaultRequestEntity(), ApiError.class);

        assertThat(responseEntity.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody())
                .isEqualTo(expected);
    }

    @Test
    public void getPrice_missingBrand() {
        ApiError expected = ApiErrorFixture.missingParameter("brand");
        ResponseEntity<ApiError> responseEntity = this.testRestTemplate.exchange("/v1/prices?date=2021-01-01T17:20:00.000-03:00&product_id=1", HttpMethod.GET, this.getDefaultRequestEntity(), ApiError.class);

        assertThat(responseEntity.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody())
                .isEqualTo(expected);
    }

    @Test
    public void getPrice_invalidBrand() {
        ApiError expected = ApiErrorFixture.badRequest("Invalid brand.");
        ResponseEntity<ApiError> responseEntity = this.testRestTemplate.exchange("/v1/prices?date=2021-01-01T17:20:00.000-03:00&product_id=1&brand=FAKE", HttpMethod.GET, this.getDefaultRequestEntity(), ApiError.class);

        assertThat(responseEntity.getStatusCode())
                .isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(responseEntity.getBody())
                .isEqualTo(expected);
    }
}
