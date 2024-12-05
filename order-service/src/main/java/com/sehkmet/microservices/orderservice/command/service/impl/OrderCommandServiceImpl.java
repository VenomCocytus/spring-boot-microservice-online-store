package com.sehkmet.microservices.orderservice.command.service.impl;

import com.sehkmet.microservices.orderservice.client.InventoryClient;
import com.sehkmet.microservices.orderservice.command.dto.PlaceOrderRequest;
import com.sehkmet.microservices.orderservice.command.dto.PlaceOrderResponse;
import com.sehkmet.microservices.orderservice.command.service.OrderCommandService;
import com.sehkmet.microservices.orderservice.exception.runtime.ProductNotInStockException;
import com.sehkmet.microservices.orderservice.mapper.OrderMapper;
import com.sehkmet.microservices.orderservice.model.Order;
import com.sehkmet.microservices.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.sehkmet.utils.utils.Utils.translate;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class OrderCommandServiceImpl implements OrderCommandService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final OrderMapper orderMapper;

    @Override
    public PlaceOrderResponse placeOrder(PlaceOrderRequest placeOrderRequest) {

        var isProductInStock = inventoryClient.isInStock(placeOrderRequest.skuCode(),
                placeOrderRequest.quantity());

        if(!isProductInStock)
            throw new ProductNotInStockException(translate("exception.product-not-in-stock", placeOrderRequest.skuCode()));

        Order orderToSave = orderMapper.mapToOrder(placeOrderRequest);
        orderToSave.setOrderNumber(UUID.randomUUID().toString());
        orderRepository.save(orderToSave);

        return orderMapper.mapToOrderResponse(orderToSave);
    }
}
