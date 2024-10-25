package com.sehkmet.microservices.orderservice.command.service;

import com.sehkmet.microservices.orderservice.command.dto.PlaceOrderRequest;

public interface OrderCommandService {

    public String placeOrder(PlaceOrderRequest placeOrderRequest);
}
