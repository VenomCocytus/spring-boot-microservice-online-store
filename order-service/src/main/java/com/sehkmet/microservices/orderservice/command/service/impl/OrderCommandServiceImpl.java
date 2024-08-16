package com.sehkmet.microservices.orderservice.command.service.impl;

import com.github.f4b6a3.uuid.UuidCreator;
import com.sehkmet.microservices.orderservice.command.dto.PlaceOrderRequestRecord;
import com.sehkmet.microservices.orderservice.command.service.OrderCommandService;
import com.sehkmet.microservices.orderservice.model.Order;
import com.sehkmet.microservices.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderCommandServiceImpl implements OrderCommandService {

    private final OrderRepository orderRepository;

    @Override
    public String placeOrder(PlaceOrderRequestRecord placeOrderRequestRecord) {

        String orderCommandId = String.valueOf(UuidCreator.getTimeOrderedEpoch());
        Order order = mapToOrder(placeOrderRequestRecord);

        orderRepository.save(order);

        log.info("Order placed successfully");

        return orderCommandId;
    }

    private static Order mapToOrder(PlaceOrderRequestRecord placeOrderRequestRecord) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(placeOrderRequestRecord.price());
        order.setQuantity(placeOrderRequestRecord.quantity());
        order.setSkuCode(placeOrderRequestRecord.skuCode());
        return order;
    }
}
