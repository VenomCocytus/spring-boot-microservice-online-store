package com.sehkmet.microservices.productservice.services;

import com.sehkmet.microservices.productservice.command.dto.CreateProductRequestRecord;
import com.sehkmet.microservices.productservice.common.request.CommandRequests;
import com.sehkmet.microservices.productservice.config.TestcontainersConfiguration;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;

import java.math.BigDecimal;

import static com.sehkmet.microservices.productservice.common.ProductServiceApiSpecification.createProductRequestSpec;
import static com.sehkmet.microservices.productservice.common.ProductServiceApiSpecification.createProductResponseSpec;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
@Epic("Product Service API")
@Feature("Product Management")
@Story("CRUD on products")
class ProductCommandServiceApplicationTests {

    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;

        // Register a custom parser for plain text
        RestAssured.registerParser("text/plain", Parser.TEXT);

        RestAssured.urlEncodingEnabled = false;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    static {
        mongoDBContainer.start();
    }

    @AfterEach
    void reset() {
        RestAssured.reset();
    }

    @Test
    @Description("Testing the creation of a new product")
    void shouldCreateProduct() {
        String createProductPath = "/create";
        CreateProductRequestRecord createProductRequestRecord = CommandRequests.getProductRequest();

        RestAssured.given(createProductRequestSpec(port, createProductRequestRecord))
                .when()
                    .post(createProductPath)
                .then()
                    .spec(createProductResponseSpec())
                    .body(Matchers.notNullValue());
    }

}
