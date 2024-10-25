package com.sehkmet.microservices.productservice.command.service;

import com.sehkmet.microservices.productservice.command.dto.request.CreateProductRequest;
import com.sehkmet.microservices.productservice.command.dto.response.CreateProductResponse;

public interface ProductCommandService {

    CreateProductResponse createProduct(CreateProductRequest createProductRequest);
    void deleteProduct(String id);
}
