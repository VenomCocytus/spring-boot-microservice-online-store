package com.sehkmet.microservices.productservice.services;

import com.sehkmet.microservices.productservice.command.dto.request.CreateProductRequestDTO;
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

import static com.sehkmet.microservices.productservice.common.specification.ProductServiceApiSpecification.createProductRequestSpec;
import static com.sehkmet.microservices.productservice.common.specification.ProductServiceApiSpecification.createProductResponseSpec;

@Slf4j
@Epic("Product Service API")
@Feature("Product Management")
@Story("CRUD on products")
@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
        CreateProductRequestDTO createProductRequestDTO = CommandRequests.getProductRequest();

        RestAssured.given(createProductRequestSpec(port, createProductRequestDTO))
                .when()
                    .post(createProductPath)
                .then()
                    .spec(createProductResponseSpec())
                    .body(Matchers.notNullValue());
    }

    // TODO Test the get product details methods
    @Test
    @Description("Testing the retrieval of a product details")
    void shouldCreateAndRetrieveProductDetails() {
        // Create the product inside the database
        String createProductPath = "/create";
        CreateProductRequestDTO createProductRequestDTO = CommandRequests.getProductRequest();

        RestAssured.given(createProductRequestSpec(port, createProductRequestDTO))
                .when()
                .post(createProductPath)
                .then()
                .spec(createProductResponseSpec())
                .body(Matchers.notNullValue());

        // Retrieve his details
        String retrieveProductDetailsPath= "/";
        RestAssured.when();
    }


    // TODO Test the get All product method

    // TODO Test file Not Found exception

    // TODO Test Method Argument exception

    // TODO Test Contraint Violation Exception

    // TODO Test UserPathVariable doesn't exists

    // TODO Product not found exception

}
