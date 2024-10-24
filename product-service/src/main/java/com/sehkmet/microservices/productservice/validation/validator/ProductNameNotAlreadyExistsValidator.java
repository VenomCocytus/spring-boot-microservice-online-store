package com.sehkmet.microservices.productservice.validation.validator;

import com.sehkmet.microservices.productservice.repository.ProductRepository;
import com.sehkmet.microservices.productservice.validation.annotation.ProductNameNotAlreadyExists;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
@RequiredArgsConstructor
public class ProductNameNotAlreadyExistsValidator implements ConstraintValidator<ProductNameNotAlreadyExists, String> {

    private final ProductRepository productRepository;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(!StringUtils.isEmpty(value)) {
            return !productRepository.existsProductByName(value);
        }

        return true;
    }
}
