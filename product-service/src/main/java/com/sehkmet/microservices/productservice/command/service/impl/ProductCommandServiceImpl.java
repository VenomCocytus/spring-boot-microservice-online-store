package com.sehkmet.microservices.productservice.command.service.impl;

import com.sehkmet.microservices.productservice.command.dto.request.CreateProductRequest;
import com.sehkmet.microservices.productservice.command.dto.response.CreateProductResponse;
import com.sehkmet.microservices.productservice.command.service.ProductCommandService;
import com.sehkmet.microservices.productservice.mapper.ProductMapper;
import com.sehkmet.microservices.productservice.model.Product;
import com.sehkmet.microservices.productservice.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.sehkmet.microservices.productservice.utils.Utils.translate;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductCommandServiceImpl implements ProductCommandService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    /**
     * Creates a new product.
     *
     * @param createProductRequest the data transfer object containing the product details
     */
    @Override
    public CreateProductResponse createProduct(
            @Valid
            CreateProductRequest createProductRequest) {

        Product product = productMapper.mapToProduct(createProductRequest);

        // Saving to the product into the database
        productRepository.save(product);

        // Logging the data
        log.info(translate("success.product-created-successfully"));

        return productMapper
                .mapToCreateProductResponse(product);
    }

    /**
     * Delete a selected product.
     *
     * @param id the identifier of the product we want to delete
     */
    @Override
    public void deleteProduct(String id) {

        productRepository.deleteById(id);

        // Logging the data
        log.info(translate("success.product-deleted-successfully"));

    }
}
