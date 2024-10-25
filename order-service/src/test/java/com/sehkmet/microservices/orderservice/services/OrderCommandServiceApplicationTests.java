package com.sehkmet.microservices.orderservice.services;

import com.sehkmet.microservices.orderservice.command.dto.PlaceOrderRequest;
import com.sehkmet.microservices.orderservice.common.request.CommandRequests;
import com.sehkmet.microservices.orderservice.config.TestcontainersConfiguration;
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
import org.testcontainers.containers.MySQLContainer;

import static com.sehkmet.microservices.orderservice.common.specification.OrderServiceApiSpecification.placeOrderRequestSpec;
import static com.sehkmet.microservices.orderservice.common.specification.OrderServiceApiSpecification.placeOrderResponseSpec;

@Slf4j
@Epic("Order Service API")
@Feature("Order Management")
@Story("Place Order")
@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderCommandServiceApplicationTests {

    @ServiceConnection
    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.3.0");

    @LocalServerPort
    Integer port;

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
        mySQLContainer.start();
    }

    @AfterEach
    void reset() {
        RestAssured.reset();
    }

    @Test
    @Description("Test the place order endpoint")
    void shouldPlaceAnOrder() {
        // Create the order request
        PlaceOrderRequest placeOrderRequest = CommandRequests.getPlaceOrderRequest();

        RestAssured.given(placeOrderRequestSpec(port, placeOrderRequest))
                .when()
                .post()
                .then()
                .spec(placeOrderResponseSpec())
                .body(Matchers.notNullValue());
    }

}
