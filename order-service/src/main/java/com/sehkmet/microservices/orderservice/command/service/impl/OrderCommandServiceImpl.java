package com.sehkmet.microservices.orderservice.command.service.impl;

import com.github.f4b6a3.uuid.UuidCreator;
import com.sehkmet.microservices.orderservice.command.dto.PlaceOrderRequestRecord;
import com.sehkmet.microservices.orderservice.command.service.OrderCommandService;
import com.sehkmet.microservices.orderservice.model.Order;
import com.sehkmet.microservices.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderCommandServiceImpl implements OrderCommandService {

    private final OrderRepository orderRepository;

    @Override
    public String placeOrder(PlaceOrderRequestRecord placeOrderRequestRecord) {

        String orderCommandId = String.valueOf(UuidCreator.getTimeOrderedEpoch());

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(placeOrderRequestRecord.price());
        order.setSkuCode(placeOrderRequestRecord.skuCode());
        order.setQuantity(placeOrderRequestRecord.quantity());

        orderRepository.save(order);

        return orderCommandId;
    }
}
