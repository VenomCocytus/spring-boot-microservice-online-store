package com.sehkmet.microservices.productservice.common.specification;

import com.sehkmet.microservices.productservice.command.dto.request.CreateProductRequestDTO;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.springframework.http.HttpStatus;

import java.util.concurrent.TimeUnit;

public class ProductServiceApiSpecification {

    private static final String basePath = "/api/product";

    public static RequestSpecification createProductRequestSpec(int port,
                                                                CreateProductRequestDTO body) {
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
                .expectContentType("application/json")
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
