package com.zara.price.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.zara.price.controller.dto.ApiError;
import com.zara.price.controller.dto.Price;
import com.zara.price.fixture.ApiErrorFixture;
import com.zara.price.fixture.PriceFixture;
import java.time.ZonedDateTime;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PriceControllerTest extends ControllerTest {

  @ParameterizedTest
  @MethodSource("useCasesGetPrice")
  public void getPrice(String brand, Long productId, ZonedDateTime date, Price expected) {

    var url = String.format("/v1/prices?date=%s&product_id=%s&brand=%s", date, productId, brand);

    ResponseEntity<Price> responseEntity = this.testRestTemplate
        .exchange(url, HttpMethod.GET, this.getDefaultRequestEntity(), Price.class);

    assertThat(responseEntity.getStatusCode())
        .isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody())
        .isEqualTo(expected);
  }

  private static Stream<Arguments> useCasesGetPrice() {
    var expectedUseCase1 = PriceFixture.price_useCase1();
    var expectedUseCase6 = PriceFixture.price_useCase6();
    var expectedUseCase7 = PriceFixture.price_useCase7();
    return Stream.of(
        Arguments.of("ZARA", 35455L, ZonedDateTime.parse("2020-06-14T10:00:00.000-00:00"),
            expectedUseCase1), // use_case 1
        Arguments.of("ZARA", 35455L, ZonedDateTime.parse("2020-06-14T16:00:00.000-00:00"),
            expectedUseCase1), // use_case 2
        Arguments.of("ZARA", 35455L, ZonedDateTime.parse("2020-06-14T21:00:00.000-00:00"),
            expectedUseCase1), // use_case 3
        Arguments.of("ZARA", 35455L, ZonedDateTime.parse("2020-06-15T10:00:00.000-00:00"),
            expectedUseCase1), // use_case 4
        Arguments.of("ZARA", 35455L, ZonedDateTime.parse("2020-06-16T16:00:00.000-00:00"),
            expectedUseCase1), // use_case 5
        Arguments.of("ZARA", 35455L, ZonedDateTime.parse("2021-01-10T11:00:00.000-00:00"),
            expectedUseCase6), // use_case 6
        Arguments.of("ZARA", 35455L, ZonedDateTime.parse("2021-01-10T17:00:00.000-00:00"),
            expectedUseCase7) // use_case 7
    );
  }

  @Test
  public void getPrice_missingDate() {
    ApiError expected = ApiErrorFixture.missingParameter("date");
    ResponseEntity<ApiError> responseEntity = this.testRestTemplate
        .exchange("/v1/prices?product_id=1&brand=ZARA", HttpMethod.GET,
            this.getDefaultRequestEntity(), ApiError.class);

    assertThat(responseEntity.getStatusCode())
        .isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(responseEntity.getBody())
        .isEqualTo(expected);

  }

  @Test
  public void getPrice_missingProductId() {
    ApiError expected = ApiErrorFixture.missingParameter("product_id");
    ResponseEntity<ApiError> responseEntity = this.testRestTemplate
        .exchange("/v1/prices?date=2021-01-01T17:20:00.000-00:00&brand=ZARA", HttpMethod.GET,
            this.getDefaultRequestEntity(), ApiError.class);

    assertThat(responseEntity.getStatusCode())
        .isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(responseEntity.getBody())
        .isEqualTo(expected);
  }

  @Test
  public void getPrice_invalidProductId() {
    ApiError expected = ApiErrorFixture.badRequest("Invalid product.");
    ResponseEntity<ApiError> responseEntity = this.testRestTemplate
        .exchange("/v1/prices?date=2021-01-01T17:20:00.000-00:00&product_id=-1&brand=ZARA",
            HttpMethod.GET, this.getDefaultRequestEntity(), ApiError.class);

    assertThat(responseEntity.getStatusCode())
        .isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(responseEntity.getBody())
        .isEqualTo(expected);
  }

  @Test
  public void getPrice_missingBrand() {
    ApiError expected = ApiErrorFixture.missingParameter("brand");
    ResponseEntity<ApiError> responseEntity = this.testRestTemplate
        .exchange("/v1/prices?date=2021-01-01T17:20:00.000-00:00&product_id=1", HttpMethod.GET,
            this.getDefaultRequestEntity(), ApiError.class);

    assertThat(responseEntity.getStatusCode())
        .isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(responseEntity.getBody())
        .isEqualTo(expected);
  }

  @Test
  public void getPrice_invalidBrand() {
    ApiError expected = ApiErrorFixture.missingParameter("brand");
    ResponseEntity<ApiError> responseEntity = this.testRestTemplate
        .exchange("/v1/prices?date=2021-01-01T17:20:00.000-00:00&product_id=1&brand=FAKE",
            HttpMethod.GET, this.getDefaultRequestEntity(), ApiError.class);

    assertThat(responseEntity.getStatusCode())
        .isEqualTo(HttpStatus.BAD_REQUEST);
    assertThat(responseEntity.getBody())
        .isEqualTo(expected);
  }
}
