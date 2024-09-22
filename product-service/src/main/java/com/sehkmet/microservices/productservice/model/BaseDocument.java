package com.sehkmet.microservices.productservice.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class BaseDocument {
    @Id
    private String id;
}
