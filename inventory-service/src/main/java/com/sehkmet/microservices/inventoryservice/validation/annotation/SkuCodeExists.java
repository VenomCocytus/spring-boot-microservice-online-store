package com.sehkmet.microservices.inventoryservice.validation.annotation;

import com.sehkmet.microservices.inventoryservice.validation.validator.SkuCodeExistsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {SkuCodeExistsValidator.class})
public @interface SkuCodeExists {

    String message() default "{default.product.not.in.stock.with.sku.code}";

    Class<?> [] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
