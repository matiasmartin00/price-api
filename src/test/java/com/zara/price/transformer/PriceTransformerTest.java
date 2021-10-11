package com.zara.price.transformer;

import com.zara.price.fixture.PriceFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({SpringExtension.class})
public class PriceTransformerTest {

    @TestConfiguration
    static class TestContextConfiguration {
        @Bean
        public PriceTransformerImpl priceTransformer() {
            return new PriceTransformerImpl();
        }
    }

    @Autowired
    private PriceTransformerImpl priceTransformer;

    @Test
    public void to() {
        var price = PriceFixture.price();
        var expected = PriceFixture.priceDto();

        var result = priceTransformer.to(price);

        assertThat(result)
                .isEqualTo(expected);
    }
}
