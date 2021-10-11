package com.zara.price.enums;

import com.zara.price.exception.InvalidBrandException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.zara.price.enums.Brand.ZARA;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BrandTest {

    @ParameterizedTest
    @ValueSource(strings = {"zara", "ZARA"})
    public void getBrandByString(String value) {
        var result = Brand.getBrand(value);

        assertThat(result)
                .isEqualTo(ZARA);
    }

    @Test
    public void getBrandById() {
        var idZara = 1L;

        var result = Brand.getBrand(idZara);

        assertThat(result)
                .isEqualTo(ZARA);
    }


    @Test
    public void getBrandByString_invalid() {
        assertThatThrownBy(() -> Brand.getBrand("FAKE"))
                .isInstanceOf(InvalidBrandException.class)
                .hasMessage("Invalid brand.");
    }

    @Test
    public void getBrandById_invalid() {
        var idZara = 5L;

        assertThatThrownBy(() -> Brand.getBrand(idZara))
                .isInstanceOf(InvalidBrandException.class)
                .hasMessage("Invalid brand.");
    }
}
