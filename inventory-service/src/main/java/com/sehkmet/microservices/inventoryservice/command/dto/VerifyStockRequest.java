package com.sehkmet.microservices.inventoryservice.command.dto;

import com.sehkmet.microservices.inventoryservice.validation.annotation.SkuCodeExists;
import jakarta.validation.constraints.*;

public record VerifyStockRequest(
        @NotBlank(message = "{messages.inventory-sku-code-validation-not-blank-alert}")
        @SkuCodeExists
        String skuCode,

        @NotNull(message = "{messages.inventory-quantity-validation-not-null-alert}")
        @Positive(message = "{messages.inventory-quantity-positive-validation-alert}")
        @Digits(message = "{messages.inventory-quantity-digits-validation-alert}",
                integer = 6, fraction = 0)
        @Max(value = 999999, message = "{messages.inventory-quantity-max-validation-alert}")
        String quantity
) {
}
