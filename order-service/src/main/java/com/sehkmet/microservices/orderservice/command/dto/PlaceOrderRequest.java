package com.sehkmet.microservices.orderservice.command.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record PlaceOrderRequest(
        @NotBlank(message = "{messages.order-sku-code-validation-not-blank-alert}")
        String skuCode,
        @Max(value = 999999, message = "{messages.order-price-max-validation-alert}")
        @NotNull(message = "{messages.order-price-validation-not-null-alert}")
        @Digits(message = "{messages.order-price-digits-validation-alert}",
                integer = 6, fraction = 0)
        @PositiveOrZero(message = "{messages.order-price-positive-or-zero-validation-alert}")
        BigDecimal price,

        @Max(value = 999999, message = "{messages.order-quantity-max-validation-alert}")
        @NotNull(message = "{messages.order-quantity-validation-not-null-alert}")
        @Digits(message = "{messages.order-quantity-digits-validation-alert}",
                integer = 6, fraction = 0)
        @Positive(message = "{messages.order-quantity-positive-validation-alert}")
        Integer quantity) {
}
