package com.sehkmet.microservices.productservice.command.dto;

import java.math.BigDecimal;

public record CreateProductRequestRecord(String id, String name, String description, BigDecimal price) {
}
