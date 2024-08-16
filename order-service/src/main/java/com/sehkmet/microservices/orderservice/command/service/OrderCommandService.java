package com.sehkmet.microservices.orderservice.command.service;

import com.sehkmet.microservices.orderservice.command.dto.PlaceOrderRequestRecord;

public interface OrderCommandService {

    public String placeOrder(PlaceOrderRequestRecord placeOrderRequestRecord);
}
