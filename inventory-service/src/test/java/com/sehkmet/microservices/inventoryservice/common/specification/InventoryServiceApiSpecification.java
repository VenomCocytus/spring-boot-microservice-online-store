package com.sehkmet.microservices.inventoryservice.common.specification;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.springframework.http.HttpStatus;

import java.util.concurrent.TimeUnit;

public class InventoryServiceApiSpecification {

    private static final String basePath = "/api/inventory";

    public static RequestSpecification isInStockRequestSpec(int port,
                                                            String skuCode,
                                                            Integer quantity) {
        return new RequestSpecBuilder()
                .setBasePath(basePath)
                .setPort(port)
                .addParam("skuCode", skuCode)
                .addParam("quantity", quantity.toString())
                .build();
    }

    public static ResponseSpecification isInStockResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.OK.value())
                .expectContentType("application/json")
                .expectResponseTime(Matchers.lessThan(5000L), TimeUnit.MILLISECONDS)
                .expectBody(Matchers.equalTo("true"))
                .build();
    }

    public static ResponseSpecification isNotInStockResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.OK.value())
                .expectContentType("application/json")
                .expectResponseTime(Matchers.lessThan(5000L), TimeUnit.MILLISECONDS)
                .expectBody(Matchers.equalTo("false"))
                .build();
    }
}
