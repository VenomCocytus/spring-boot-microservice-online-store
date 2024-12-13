package com.sehkmet.core.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Value("${service-data.info.title}")
    private String title;

    @Value("${service-data.info.description}")
    private String description;

    @Value("${service-data.info.version}")
    private String version;

    @Value("${service-data.info.license.name}")
    private String licenseName;

    @Value("${service-data.info.license.url}")
    private String licenseUrl;

    @Value("${service-data.info.contact.name}")
    private String contactName;

    @Value("${service-data.info.contact.url}")
    private String contactUrl;

    @Value("${service-data.external-documentation.description}")
    private String externalDescription;

    @Value("${service-data.external-documentation.url}")
    private String externalUrl;

    @Bean
    public OpenAPI serviceApi() {

        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .description(description)
                        .version(version)
                        .license(new License()
                                .name(licenseName)
                                .url(licenseUrl))
                        .contact(new Contact()
                                .name(contactName)
                                .url(contactUrl)))
                .externalDocs(new ExternalDocumentation()
                        .description(externalDescription)
                        .url(externalUrl));
    }
}
