package com.zara.price.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class SpringDocConfig {

  private String title;
  private String description;
  private String version;

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI().info(new Info().title(title)
        .version(version)
        .description(description)
        .contact(new Contact().name("price-api")
            .email("matiass.lean@gmail.com")));
  }
}
