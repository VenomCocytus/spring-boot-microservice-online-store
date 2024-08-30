package com.sehkmet.microservices.productservice.command.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateProductRequestRecord(
        @Null
        String id,
        @NotNull(message = "messages.product-name-validation-not-null-alert")
        @NotEmpty(message = "messages.product-name-validation-not-empty-alert")
        @Size(min = 3, max = 50,
                message = "messages.product-name-validation-size-alert")
        String name,
        @NotNull(message="messages.product-description-validation-not-null-alert")
        @NotEmpty(message = "messages.product-description-validation-not-empty-alert")
        @Size(min = 3, max = 500,
                message = "messages.product-description-validation-size-alert")
        String description,
        @NotNull(message = "messages.product-price-validation-not-null-alert")
        @NotEmpty(message = "messages.product-price-validation-not-empty-alert")
        BigDecimal price
) {}
