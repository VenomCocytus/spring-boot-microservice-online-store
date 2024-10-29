package com.sehkmet.microservices.orderservice.common.request;

import com.sehkmet.microservices.orderservice.command.dto.PlaceOrderRequest;

import java.math.BigDecimal;

public class CommandRequests {

    public static PlaceOrderRequest placeOrderWithStockRequest() {
        return new PlaceOrderRequest(
                "iphone_15",
                BigDecimal.valueOf(1000),
                1

        );
    }

    public static PlaceOrderRequest placeOrderWithoutStockRequest() {
        return new PlaceOrderRequest(
                "iphone_15",
                BigDecimal.valueOf(1000),
                1000

        );
    }
}
