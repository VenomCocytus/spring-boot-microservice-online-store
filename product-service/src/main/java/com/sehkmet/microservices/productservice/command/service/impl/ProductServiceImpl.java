package com.sehkmet.microservices.productservice.command.service.impl;

import com.sehkmet.microservices.productservice.command.dto.CreateProductRequestDTO;
import com.sehkmet.microservices.productservice.command.service.ProductService;
import com.sehkmet.microservices.productservice.command.service.handler.ProductCommandEventHandler;
import com.sehkmet.microservices.productservice.model.Product;
import com.sehkmet.microservices.productservice.repository.ProductRepository;
import com.sehkmet.microservices.productservice.validator.ProductValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCommandEventHandler productCommandEventHandler;


    /**
     * Creates a new product.
     *
     * @param createProductRequestDTO the data transfer object containing the product details
     */
    @Override
    public String createProduct(CreateProductRequestDTO createProductRequestDTO) {

        ProductValidator.create(createProductRequestDTO);


        //TODO Create a time based id generator

//        this.productCommandEventHandler.on(new ProductCreationEvent(
//
//        ));

        Product product = Product.builder()
                .name(createProductRequestDTO.name())
                .description(createProductRequestDTO.description())
                .price(createProductRequestDTO.price())
                .build();

        // Saving to the product into the database
        productRepository.save(product);

        // Logging the data
        log.info("Product created successfully");

        return "Ok";
    }
}
