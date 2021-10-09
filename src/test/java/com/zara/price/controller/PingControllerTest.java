package com.zara.price.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class PingControllerTest  extends ControllerTest {
    @Test
    void ping() {
        ResponseEntity<String> responseEntity = this.testRestTemplate.exchange("/ping", HttpMethod.GET, this.getDefaultRequestEntity(), String.class);

        assertThat(responseEntity.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody())
                .isEqualTo("pong");
    }
}
