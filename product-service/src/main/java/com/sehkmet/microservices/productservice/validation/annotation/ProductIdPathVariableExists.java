package com.sehkmet.microservices.productservice.validation.annotation;

import com.sehkmet.microservices.productservice.validation.validator.ProductIdPathVariableExistsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ProductIdPathVariableExistsValidator.class})
public @interface ProductIdPathVariableExists {

    String message() default "default.product-not-found-path-variable";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
