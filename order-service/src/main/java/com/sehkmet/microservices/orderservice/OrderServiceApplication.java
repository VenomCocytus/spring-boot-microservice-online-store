package com.sehkmet.microservices.orderservice;

import com.sehkmet.core.component.OpenApiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients(basePackages = "com.sehkmet")
@SpringBootApplication(scanBasePackages = "com.sehkmet")
@EnableConfigurationProperties(OpenApiProperties.class)
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

}
