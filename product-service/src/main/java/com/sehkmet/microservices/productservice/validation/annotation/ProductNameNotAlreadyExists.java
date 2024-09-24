package com.sehkmet.microservices.productservice.validation.annotation;

import com.sehkmet.microservices.productservice.validation.validator.ProductNameNotAlreadyExistsValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ProductNameNotAlreadyExistsValidator.class})
public @interface ProductNameNotAlreadyExists {

    String message() default "{default.product-already-exist-with-name}";

    Class<?> [] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
