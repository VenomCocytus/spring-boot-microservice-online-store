package com.sehkmet.microservices.orderservice.command.service.impl;

import com.sehkmet.client.inventory.client.InventoryClient;
import com.sehkmet.client.inventory.dto.VerifyStockRequest;
import com.sehkmet.microservices.orderservice.command.dto.PlaceOrderRequest;
import com.sehkmet.microservices.orderservice.command.dto.PlaceOrderResponse;
import com.sehkmet.microservices.orderservice.command.service.OrderCommandService;
import com.sehkmet.microservices.orderservice.event.OrderPlacedEvent;
import com.sehkmet.microservices.orderservice.exception.runtime.ProductNotInStockException;
import com.sehkmet.microservices.orderservice.mapper.OrderMapper;
import com.sehkmet.microservices.orderservice.model.Order;
import com.sehkmet.microservices.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.logging.Logger;

import static com.sehkmet.utils.utils.Utils.translate;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class OrderCommandServiceImpl implements OrderCommandService {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(OrderCommandServiceImpl.class);

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final OrderMapper orderMapper;

    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    @Override
    public PlaceOrderResponse placeOrder(PlaceOrderRequest placeOrderRequest) {

        var clientResponse = inventoryClient.isInStock(
                new VerifyStockRequest(placeOrderRequest.skuCode(),
                        String.valueOf(placeOrderRequest.quantity())));

        if(!clientResponse.getStatusCode().is2xxSuccessful())
            throw new ProductNotInStockException(
                    translate("exception.product-not-in-stock", placeOrderRequest.skuCode()));

        Order orderToSave = orderMapper.mapToOrder(placeOrderRequest);
        orderToSave.setOrderNumber(UUID.randomUUID().toString());
        orderToSave.setPrice(placeOrderRequest.price()
                .multiply(BigDecimal.valueOf(placeOrderRequest.quantity())));
        orderRepository.save(orderToSave);

        var orderPlacedEvent = new OrderPlacedEvent(order.getOrderNumber(), orderRequest.userDetails()
                .email(),
                orderRequest.userDetails()
                        .firstName(),
                orderRequest.userDetails()
                        .lastName());

        return orderMapper.mapToOrderResponse(orderToSave);
    }
}
