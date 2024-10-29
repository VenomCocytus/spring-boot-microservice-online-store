package com.sehkmet.microservices.orderservice.common.specification;

import com.sehkmet.microservices.orderservice.command.dto.PlaceOrderRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.springframework.http.HttpStatus;

import java.util.concurrent.TimeUnit;

import static org.springframework.cloud.contract.spec.internal.MediaTypes.APPLICATION_JSON;
import static org.springframework.cloud.contract.spec.internal.MediaTypes.TEXT_PLAIN;

public class OrderServiceApiSpecification {

    private static final String basePath = "/api/order";

    public static RequestSpecification placeOrderRequestSpec(int port,
                                                             PlaceOrderRequest body) {
        return new RequestSpecBuilder()
                .setBasePath(basePath)
                .setPort(port)
                .setContentType(APPLICATION_JSON)
                .setBody(body)
                .build();
    }

    public static ResponseSpecification placeOrderWithStockResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.CREATED.value())
                .expectContentType(TEXT_PLAIN)
                .expectResponseTime(Matchers.lessThan(5000L), TimeUnit.MILLISECONDS)
                .expectBody(Matchers.notNullValue())
                .build();
    }

    public static ResponseSpecification placeOrderWithoutStockResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .expectContentType(APPLICATION_JSON)
                .expectResponseTime(Matchers.lessThan(5000L), TimeUnit.MILLISECONDS)
                .build();
    }
}
