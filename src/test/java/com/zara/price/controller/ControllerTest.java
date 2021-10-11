package com.zara.price.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zara.price.Application;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

    @Autowired
    protected TestRestTemplate testRestTemplate;

    @Autowired
    protected ObjectMapper objectMapper;

    protected <T> RequestEntity<T> getDefaultRequestEntity() {
        HttpHeaders headers = new HttpHeaders();
        return new RequestEntity<>(headers, HttpMethod.GET, null);
    }

    protected <T> RequestEntity<T> getRequestEntity(T body) {
        HttpHeaders headers = new HttpHeaders();
        return new RequestEntity<>(body, headers, HttpMethod.POST, null);
    }
}
