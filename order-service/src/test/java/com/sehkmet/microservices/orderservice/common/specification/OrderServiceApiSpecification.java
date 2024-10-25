package com.sehkmet.microservices.orderservice.common.specification;

import com.sehkmet.microservices.orderservice.command.dto.PlaceOrderRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.springframework.http.HttpStatus;

import java.util.concurrent.TimeUnit;

public class OrderServiceApiSpecification {

    private static final String basePath = "/api/order";

    public static RequestSpecification placeOrderRequestSpec(int port,
                                                                PlaceOrderRequest body) {
        return new RequestSpecBuilder()
                .setBasePath(basePath)
                .setPort(port)
                .setContentType("application/json")
                .setBody(body)
                .build();
    }

    public static ResponseSpecification placeOrderResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.CREATED.value())
                .expectContentType("application/json")
                .expectResponseTime(Matchers.lessThan(5000L), TimeUnit.MILLISECONDS)
                .expectBody("timestamp", Matchers.notNullValue())
                .build();
    }
}
