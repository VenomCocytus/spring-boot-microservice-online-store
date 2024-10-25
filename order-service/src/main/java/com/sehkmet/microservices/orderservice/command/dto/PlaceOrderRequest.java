package com.sehkmet.microservices.orderservice.command.dto;

import java.math.BigDecimal;

public record PlaceOrderRequest(String skuCode, BigDecimal price, Integer quantity) {
}
