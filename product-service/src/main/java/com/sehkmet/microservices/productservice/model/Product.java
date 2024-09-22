package com.sehkmet.microservices.productservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(value = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Product extends BaseDocument {

    @NotBlank(message = "messages.product-name-validation-not-blank-alert")
    @Size(min = 3, max = 50,
            message = "messages.product-name-validation-size-alert")
    private String name;

    @NotBlank(message = "messages.product-description-validation-not-blank-alert")
    @Size(min = 3, max = 500,
            message = "messages.product-description-validation-size-alert")
    private String description;

    @NotNull(message = "messages.product-price-validation-not-null-alert")
    private BigDecimal price;
}
