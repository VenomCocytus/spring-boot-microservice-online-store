package com.sehkmet.microservices.productservice.query.service.impl;

import com.sehkmet.microservices.productservice.query.dto.CreateProductResponse;
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

    /**
     * @return a list of all products
     */
    @Override
    public List<CreateProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(product -> new CreateProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice()
                )).toList();
    }
}
