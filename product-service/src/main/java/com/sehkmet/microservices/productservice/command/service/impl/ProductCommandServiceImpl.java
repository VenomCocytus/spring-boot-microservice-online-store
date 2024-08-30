package com.sehkmet.microservices.productservice.command.service.impl;

import com.github.f4b6a3.uuid.UuidCreator;
import com.sehkmet.microservices.productservice.command.dto.CreateProductRequestRecord;
import com.sehkmet.microservices.productservice.command.service.ProductCommandService;
import com.sehkmet.microservices.productservice.model.Product;
import com.sehkmet.microservices.productservice.repository.ProductRepository;
import com.sehkmet.microservices.productservice.validator.ProductValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductCommandServiceImpl implements ProductCommandService {

    private final ProductRepository productRepository;


    /**
     * Creates a new product.
     *
     * @param createProductRequestRecord the data transfer object containing the product details
     */
    @Override
    public String createProduct(@Valid CreateProductRequestRecord createProductRequestRecord) {

        ProductValidator.create(createProductRequestRecord);

        String productCommandId = String.valueOf(UuidCreator.getTimeOrderedEpoch());

        Product product = Product.builder()
                .name(createProductRequestRecord.name())
                .description(createProductRequestRecord.description())
                .price(createProductRequestRecord.price())
                .build();

        // Saving to the product into the database
        productRepository.save(product);

        // Logging the data
        log.info("Product created successfully");

        return productCommandId;
    }
}
