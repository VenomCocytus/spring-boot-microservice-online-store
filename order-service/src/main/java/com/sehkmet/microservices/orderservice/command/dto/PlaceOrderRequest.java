package com.sehkmet.microservices.orderservice.command.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.AUTO;

public record PlaceOrderRequest(
        @Schema(description = "The order Sku Code", example = "Sku Code", requiredMode = AUTO)
        @NotBlank(message = "{messages.order-sku-code-validation-not-blank-alert}")
        String skuCode,

        @Schema(description = "The price of the product", example = "19.99", requiredMode = AUTO)
        @Max(value = 999999, message = "{messages.order-price-max-validation-alert}")
        @NotNull(message = "{messages.order-price-validation-not-null-alert}")
        @Digits(message = "{messages.order-price-digits-validation-alert}",
                integer = 6, fraction = 0)
        @PositiveOrZero(message = "{messages.order-price-positive-or-zero-validation-alert}")
        BigDecimal price,

        @Schema(description = "The quantity of the product", example = "10", requiredMode = AUTO)
        @Max(value = 999999, message = "{messages.order-quantity-max-validation-alert}")
        @NotNull(message = "{messages.order-quantity-validation-not-null-alert}")
        @Digits(message = "{messages.order-quantity-digits-validation-alert}",
                integer = 6, fraction = 0)
        @Positive(message = "{messages.order-quantity-positive-validation-alert}")
        Integer quantity) {
}
