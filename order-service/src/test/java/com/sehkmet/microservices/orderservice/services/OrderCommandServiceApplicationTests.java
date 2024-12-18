package com.sehkmet.microservices.orderservice.services;

import com.sehkmet.microservices.orderservice.client.InventoryClientStub;
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
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MySQLContainer;

import static com.sehkmet.microservices.orderservice.common.specification.OrderServiceApiSpecification.*;

@Slf4j
@Epic("Order Service API")
@Feature("Order Management")
@Story("Place Order")
@AutoConfigureWireMock(port = 0)
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
    @Description("Test if we can submit an order")
    void shouldPlaceAnOrder() {
        // Create the order request
        PlaceOrderRequest placeOrderWithStockRequest = CommandRequests.placeOrderWithStockRequest();

        // Calling the inventory stub
        InventoryClientStub.stubInventoryCallIsInStock(placeOrderWithStockRequest);

        RestAssured.given(placeOrderRequestSpec(port, placeOrderWithStockRequest))
                .when()
                .post()
                .then()
                .spec(placeOrderWithStockResponseSpec())
                .body(Matchers.notNullValue());
    }

    @Test
    @Description("Test if we cannot submit an order")
    void shouldNotPlaceAnOrder() {
        // Create the order request
        PlaceOrderRequest placeOrderWithoutStockRequest = CommandRequests.placeOrderWithoutStockRequest();

        // Calling the inventory stub
        InventoryClientStub.stubInventoryCallIsNotInStock(placeOrderWithoutStockRequest);

        RestAssured.given(placeOrderRequestSpec(port, placeOrderWithoutStockRequest))
                .when()
                .post()
                .then()
                .spec(placeOrderWithoutStockResponseSpec())
                .body(Matchers.notNullValue());
    }

}
