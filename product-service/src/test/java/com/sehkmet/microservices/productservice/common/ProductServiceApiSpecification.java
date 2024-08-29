package com.sehkmet.microservices.productservice.common;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.springframework.http.HttpStatus;

import java.util.concurrent.TimeUnit;

public class ProductServiceApiSpecification {

    private static final String basePath = "/api/product";

    public static RequestSpecification createProductRequestSpec(int port,
                                                                String body) {
        return new RequestSpecBuilder()
                .setBasePath(basePath)
                .setPort(port)
                .setContentType("application/json")
                .setBody(body)
                .build();
    }

    public static ResponseSpecification createProductResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.CREATED.value())
                .expectContentType(ContentType.TEXT)
                .expectResponseTime(Matchers.lessThan(5000L), TimeUnit.MILLISECONDS)
//                .expectBody("size()", Matchers.greaterThan(0))
                .build();
    }
}
