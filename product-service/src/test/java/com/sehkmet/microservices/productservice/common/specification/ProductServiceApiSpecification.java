package com.sehkmet.microservices.productservice.common.specification;

import com.sehkmet.microservices.productservice.command.dto.command.request.CreateProductRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.springframework.http.HttpStatus;

import java.util.concurrent.TimeUnit;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class ProductServiceApiSpecification {

    private static final String basePath = "/api/product";

    public static RequestSpecification createProductRequestSpec(int port,
                                                                CreateProductRequest body) {
        return new RequestSpecBuilder()
                .setBasePath(basePath)
                .setPort(port)
                .setContentType(APPLICATION_JSON_VALUE)
                .setBody(body)
                .build();
    }

    public static ResponseSpecification createProductResponseSpec() {
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.CREATED.value())
                .expectContentType(APPLICATION_JSON_VALUE)
                .expectResponseTime(Matchers.lessThan(5000L), TimeUnit.MILLISECONDS)
                .expectBody("timestamp", Matchers.notNullValue())
                .expectBody("success", Matchers.equalTo(true))
                .expectBody("message", Matchers.is("Product created successfully."))
                .expectBody("data.id", Matchers.notNullValue())
                .expectBody("data.name", Matchers.is("Pokedex"))
                .expectBody("data.description", Matchers.equalTo("A tool register data about encountered pokemon"))
                .expectBody("data.price", Matchers.equalTo(2000))
                .build();
    }
}
