package com.sehkmet.microservices.orderservice.command.dto;

import java.math.BigDecimal;

public record PlaceOrderRequestRecord(Long id, String orderNumber, String skuCode, BigDecimal price, Integer quantity) {
}
