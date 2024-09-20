package com.sehkmet.microservices.productservice.command.dto.response;

import java.math.BigDecimal;

public record CreateProductResponseDTO(
        String id,
        String name,
        String description,
        BigDecimal price
) {}
