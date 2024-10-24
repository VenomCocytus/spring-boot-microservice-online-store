package com.sehkmet.microservices.productservice;

import com.sehkmet.microservices.productservice.config.TestcontainersConfiguration;
import org.springframework.boot.SpringApplication;

public class TestProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(ProductServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
