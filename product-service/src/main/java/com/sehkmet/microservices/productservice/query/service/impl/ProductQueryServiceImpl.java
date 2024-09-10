package com.sehkmet.microservices.productservice.query.service.impl;

import com.sehkmet.microservices.productservice.exception.runtime.ProductNotFoundException;
import com.sehkmet.microservices.productservice.mapper.ProductMapper;
import com.sehkmet.microservices.productservice.model.Product;
import com.sehkmet.microservices.productservice.query.dto.GetProductResponse;
import com.sehkmet.microservices.productservice.query.service.ProductQueryService;
import com.sehkmet.microservices.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class ProductQueryServiceImpl implements ProductQueryService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public GetProductResponse getProductDetails(String productId) {
        Product productDetails = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("exception.product-not-found-with-id")
        );

        return productMapper.productToGetProductResponse(productDetails);
    }

    /**
     * @return a list of all products
     */
    @Override
    public List<GetProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::productToGetProductResponse)
                .toList();
    }
}
