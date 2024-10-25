package com.sehkmet.microservices.orderservice.command.service.impl;

import com.github.f4b6a3.uuid.UuidCreator;
import com.sehkmet.microservices.orderservice.client.InventoryClient;
import com.sehkmet.microservices.orderservice.command.dto.PlaceOrderRequest;
import com.sehkmet.microservices.orderservice.command.service.OrderCommandService;
import com.sehkmet.microservices.orderservice.mapper.OrderMapper;
import com.sehkmet.microservices.orderservice.model.Order;
import com.sehkmet.microservices.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class OrderCommandServiceImpl implements OrderCommandService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final OrderMapper orderMapper;

    @Override
    public String placeOrder(PlaceOrderRequest placeOrderRequest) {

        var isProductInStock = inventoryClient.isInStock(placeOrderRequest.skuCode(),
                placeOrderRequest.quantity());

        if (isProductInStock) {

            String orderCommandId = String.valueOf(UuidCreator.getTimeOrderedEpoch());
            Order orderToSave = orderMapper.mapToOrder(placeOrderRequest);
            orderToSave.setOrderNumber(UUID.randomUUID().toString());

            orderRepository.save(orderToSave);

            log.info("Order placed successfully");

            return orderCommandId;
        } else {
            throw new RuntimeException("Product with skuCode " + placeOrderRequest.skuCode() + " is not in stock");
        }
    }
}
