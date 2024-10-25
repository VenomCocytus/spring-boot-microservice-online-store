package com.sehkmet.microservices.inventoryservice;

import com.sehkmet.microservices.inventoryservice.config.TestcontainersConfiguration;
import org.springframework.boot.SpringApplication;

public class TestInventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(InventoryServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
