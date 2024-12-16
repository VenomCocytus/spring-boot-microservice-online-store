package com.sehkmet.microservices.inventoryservice.command.dto;

import com.sehkmet.microservices.inventoryservice.validation.annotation.SkuCodeExists;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.AUTO;

public record VerifyStockRequest(
        @Schema(description = "The order Sku Code", example = "Sku Code", requiredMode = AUTO)
        @NotBlank(message = "{messages.inventory-sku-code-validation-not-blank-alert}")
        @SkuCodeExists
        String skuCode,

        @Schema(description = "The quantity of the product", example = "10", requiredMode = AUTO)
        @NotNull(message = "{messages.inventory-quantity-validation-not-null-alert}")
        @Positive(message = "{messages.inventory-quantity-positive-validation-alert}")
        @Digits(message = "{messages.inventory-quantity-digits-validation-alert}",
                integer = 6, fraction = 0)
        @Max(value = 999999, message = "{messages.inventory-quantity-max-validation-alert}")
        String quantity
) {
}
