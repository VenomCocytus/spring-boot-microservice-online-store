package com.sehkmet.core.config;

import com.sehkmet.core.component.OpenApiProperties;
import io.swagger.v3.oas.models.OpenAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class OpenApiConfig {

    private final OpenApiProperties openApiProperties;

    @Bean
    public OpenAPI serviceApi() {

        return new OpenAPI()
                .info(openApiProperties.getInfo())
                .externalDocs(openApiProperties.getExternalDocumentation());
    }
}
