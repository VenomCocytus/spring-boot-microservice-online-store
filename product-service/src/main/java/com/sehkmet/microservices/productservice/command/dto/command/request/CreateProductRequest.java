package com.sehkmet.microservices.productservice.command.dto.command.request;

import com.sehkmet.microservices.productservice.validation.annotation.ProductNameNotAlreadyExists;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateProductRequest(
        @NotBlank(message = "{messages.product-name-validation-not-blank-alert}")
        @Size(min = 3, max = 50,
                message = "{messages.product-name-validation-size-alert}")
        @ProductNameNotAlreadyExists
        String name,
        @NotBlank(message="{messages.product-description-validation-not-blank-alert}")
        @Size(min = 3, max = 500,
                message = "{messages.product-description-validation-size-alert}")
        String description,
        @NotNull(message = "{messages.product-price-validation-not-null-alert}")
        BigDecimal price
) {}
