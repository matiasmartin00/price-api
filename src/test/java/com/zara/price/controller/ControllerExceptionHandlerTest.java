package com.zara.price.controller;

import com.zara.price.controller.dto.ApiError;
import com.zara.price.fixture.ApiErrorFixture;
import org.junit.jupiter.api.Test;
import org.mockito.exceptions.base.MockitoException;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doThrow;

public class ControllerExceptionHandlerTest extends ControllerTest {


    @SpyBean
    private PingController pingController;

    @Test
    public void notFound() {
        ApiError expected = ApiErrorFixture.notFound("/fake");
        ResponseEntity<ApiError> responseEntity = this.testRestTemplate.exchange("/fake", HttpMethod.GET, this.getDefaultRequestEntity(), ApiError.class);

        assertThat(responseEntity.getStatusCode())
                .isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(responseEntity.getBody())
                .isEqualTo(expected);
    }

    @Test
    public void unhandledException() {
        ApiError expected = ApiErrorFixture.internalError();
        doThrow(new MockitoException("any error")).when(pingController)
                .ping();

        ResponseEntity<ApiError> responseEntity = this.testRestTemplate.exchange("/ping", HttpMethod.GET, this.getDefaultRequestEntity(), ApiError.class);

        assertThat(responseEntity.getStatusCode())
            .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(responseEntity.getBody())
                .isEqualTo(expected);
    }

    @Test
    public void methodNoSupport() {
        ApiError expected = ApiErrorFixture.methodNotSupport("POST", "/ping");

        ResponseEntity<ApiError> responseEntity = this.testRestTemplate.exchange("/ping", HttpMethod.POST, this.getDefaultRequestEntity(), ApiError.class);

        assertThat(responseEntity.getStatusCode())
                .isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);
        assertThat(responseEntity.getBody())
                .isEqualTo(expected);
    }
}
