package com.sehkmet.microservices.inventoryservice.validation.validator;

import com.sehkmet.microservices.inventoryservice.repository.InventoryRepository;
import com.sehkmet.microservices.inventoryservice.validation.annotation.SkuCodeExists;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@RequiredArgsConstructor
public class SkuCodeExistsValidator implements ConstraintValidator<SkuCodeExists, String> {

    private final InventoryRepository inventoryRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(!StringUtils.isEmpty(value)) {
            return inventoryRepository.existsBySkuCode(value);
        }

        return false;
    }
}
