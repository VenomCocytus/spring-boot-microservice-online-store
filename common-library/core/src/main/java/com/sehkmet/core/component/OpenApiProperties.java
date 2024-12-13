package com.sehkmet.core.component;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.ExternalDocumentation;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "service-data")
public class OpenApiProperties {

    private Info info;
    private ExternalDocumentation externalDocumentation;
}
