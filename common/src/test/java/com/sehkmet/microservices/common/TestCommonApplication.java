package com.sehkmet.microservices.common;

import org.springframework.boot.SpringApplication;

public class TestCommonApplication {

    public static void main(String[] args) {
        SpringApplication.from(CommonApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
