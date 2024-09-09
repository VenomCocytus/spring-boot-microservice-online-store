package com.sehkmet.microservices.productservice.query.dto;

import java.math.BigDecimal;

public record GetProductResponse(
        String id,
        String name,
        String description,
        BigDecimal price
) {}
