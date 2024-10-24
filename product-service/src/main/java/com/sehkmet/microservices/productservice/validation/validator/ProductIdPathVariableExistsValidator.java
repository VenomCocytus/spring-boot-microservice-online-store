package com.sehkmet.microservices.productservice.validation.validator;

import com.sehkmet.microservices.productservice.repository.ProductRepository;
import com.sehkmet.microservices.productservice.validation.annotation.ProductIdPathVariableExists;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductIdPathVariableExistsValidator implements ConstraintValidator<ProductIdPathVariableExists, String> {

    private final ProductRepository productRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        if(!StringUtils.isEmpty(value)) {
            return productRepository.findById(value).isPresent();
        }

        return false;
    }
}
