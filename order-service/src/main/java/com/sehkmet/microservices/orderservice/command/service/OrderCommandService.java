package com.sehkmet.microservices.orderservice.command.service;

import com.sehkmet.microservices.orderservice.command.dto.PlaceOrderRequest;

public interface OrderCommandService {

    String placeOrder(PlaceOrderRequest placeOrderRequest);
}
