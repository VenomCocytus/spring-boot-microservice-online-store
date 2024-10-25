package com.sehkmet.microservices.orderservice.common.request;

import com.sehkmet.microservices.orderservice.command.dto.PlaceOrderRequest;

import java.math.BigDecimal;

public class CommandRequests {

    public static PlaceOrderRequest getPlaceOrderRequest() {
        return new PlaceOrderRequest(
                "iphone_15",
                BigDecimal.valueOf(1000),
                1

        );
    }
}
