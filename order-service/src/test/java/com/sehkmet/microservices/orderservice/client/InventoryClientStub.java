package com.sehkmet.microservices.orderservice.client;

import com.sehkmet.microservices.orderservice.command.dto.PlaceOrderRequest;
import org.springframework.http.HttpStatus;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

public class InventoryClientStub {

    public static void stubInventoryCallIsInStock(PlaceOrderRequest placeOrderRequest){
        stubFor(get(urlEqualTo(
                "/api/inventory?skuCode=" + placeOrderRequest.skuCode() +
                        "&quantity=" + placeOrderRequest.quantity()))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody("true")));
    }

    public static void stubInventoryCallIsNotInStock(PlaceOrderRequest placeOrderRequest){
        stubFor(get(urlEqualTo(
                "/api/inventory?skuCode=" + placeOrderRequest.skuCode() +
                        "&quantity=" + placeOrderRequest.quantity()))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody("false")));
    }
}
