package com.sehkmet.microservices.inventoryservice.service;

import com.sehkmet.microservices.inventoryservice.config.TestcontainersConfiguration;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
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

import static com.sehkmet.microservices.inventoryservice.common.specification.InventoryServiceApiSpecification.*;

@Slf4j
@Epic("Inventory Service API")
@Feature("Inventory Management")
@Story("Check if a product is in stock")
@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryServiceApplicationTests {

    @ServiceConnection
    static MySQLContainer mySQLContainer = new MySQLContainer<>("mysql:8.3.0");

    @LocalServerPort
    Integer port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;

        RestAssured.urlEncodingEnabled = false;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @AfterEach
    void reset() {
        RestAssured.reset();
    }

    static {
        mySQLContainer.start();
    }

    @Test
    @Description("Checking if a selected product is in stock")
    void shouldBeInStock() {
        RestAssured.given(isInStockRequestSpec(port, "iphone_15", 1))
                .when()
                .get()
                .then()
                .spec(isInStockResponseSpec());
    }

    @Test
    @Description("Checking if a selected product is not in stock")
    void shouldNotBeInStock() {
        RestAssured.given(isInStockRequestSpec(port, "iphone_15", 1000))
                .when()
                .get()
                .then()
                .spec(isNotInStockResponseSpec());
    }

}
