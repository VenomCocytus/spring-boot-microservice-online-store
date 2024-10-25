package com.sehkmet.microservices.orderservice.common.request;

import com.sehkmet.microservices.orderservice.command.dto.PlaceOrderRequest;

import java.math.BigDecimal;

public class CommandRequests {

    public static PlaceOrderRequest getPlaceOrderRequest() {
        return new PlaceOrderRequest(
                "",
                "A tool register data about encountered pokemon",
                BigDecimal.valueOf(2000)
        );
    }
}
