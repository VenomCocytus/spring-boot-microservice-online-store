package com.sehkmet.microservices.productservice.command.dto.command.request;

import com.sehkmet.microservices.productservice.validation.annotation.ProductNameNotAlreadyExists;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.AUTO;

public record CreateProductRequest(
        @Schema(description = "The name of the product", example = "An awesome product", requiredMode = AUTO)
        @NotBlank(message = "{messages.product-name-validation-not-blank-alert}")
        @Size(min = 3, max = 50,
                message = "{messages.product-name-validation-size-alert}")
        @ProductNameNotAlreadyExists
        String name,

        @Schema(description = "The description of the product", example = "This is an amazing product", requiredMode = AUTO)
        @Size(min = 3, max = 500,
                message = "{messages.product-description-validation-size-alert}")
        @NotBlank(message="{messages.product-description-validation-not-blank-alert}")
        @Size(min = 3, max = 500,
                message = "{messages.product-description-validation-size-alert}")
        String description,

        @Schema(description = "The price of the product", example = "19.99", requiredMode = AUTO)
        @NotNull(message = "{messages.product-price-validation-not-null-alert}")
        BigDecimal price
) {}
