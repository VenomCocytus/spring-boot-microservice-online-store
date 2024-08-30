package com.sehkmet.microservices.productservice.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {

    @Id
    private String Id;

    @NotNull(message = "messages.product-name-validation-not-null-alert")
    @NotEmpty(message = "messages.product-name-validation-not-empty-alert")
    @Size(min = 3, max = 50,
            message = "messages.product-name-validation-size-alert")
    private String name;

    @NotNull(message="messages.product-description-validation-not-null-alert")
    @NotEmpty(message = "messages.product-description-validation-not-empty-alert")
    @Size(min = 3, max = 500,
            message = "messages.product-description-validation-size-alert")
    private String description;

    @NotNull(message = "messages.product-price-validation-not-null-alert")
    @NotEmpty(message = "messages.product-price-validation-not-empty-alert")
    private BigDecimal price;
}
