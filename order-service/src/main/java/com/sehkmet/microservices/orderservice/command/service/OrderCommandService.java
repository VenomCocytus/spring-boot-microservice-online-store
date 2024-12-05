package com.sehkmet.microservices.orderservice.command.service;

import com.sehkmet.microservices.orderservice.command.dto.PlaceOrderRequest;
import com.sehkmet.microservices.orderservice.command.dto.PlaceOrderResponse;

public interface OrderCommandService {

    PlaceOrderResponse placeOrder(PlaceOrderRequest placeOrderRequest);
}
