package com.zara.price.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

import com.zara.price.controller.dto.ApiError;
import com.zara.price.fixture.ApiErrorFixture;
import org.junit.jupiter.api.Test;
import org.mockito.exceptions.base.MockitoException;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ControllerExceptionHandlerTest extends ControllerTest {


  @SpyBean
  private PriceController priceController;

  @Test
  public void notFound() {
    ApiError expected = ApiErrorFixture.notFound("/fake");
    ResponseEntity<ApiError> responseEntity = this.testRestTemplate
        .exchange("/fake", HttpMethod.GET, this.getDefaultRequestEntity(), ApiError.class);

    assertThat(responseEntity.getStatusCode())
        .isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(responseEntity.getBody())
        .isEqualTo(expected);
  }

  @Test
  public void unhandledException() {
    ApiError expected = ApiErrorFixture.internalError();
    doThrow(new MockitoException("any error")).when(priceController)
        .getPrice(any(), any(), any());

    ResponseEntity<ApiError> responseEntity = this.testRestTemplate
        .exchange("/v1/prices?date=2021-01-01T17:20:00.000-03:00&brand=ZARA&product_id=1",
            HttpMethod.GET, this.getDefaultRequestEntity(), ApiError.class);

    assertThat(responseEntity.getStatusCode())
        .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    assertThat(responseEntity.getBody())
        .isEqualTo(expected);
  }

  @Test
  public void methodNoSupport() {
    ApiError expected = ApiErrorFixture.methodNotSupport("POST", "/v1/prices");

    ResponseEntity<ApiError> responseEntity = this.testRestTemplate
        .exchange("/v1/prices", HttpMethod.POST, this.getDefaultRequestEntity(), ApiError.class);

    assertThat(responseEntity.getStatusCode())
        .isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);
    assertThat(responseEntity.getBody())
        .isEqualTo(expected);
  }
}
