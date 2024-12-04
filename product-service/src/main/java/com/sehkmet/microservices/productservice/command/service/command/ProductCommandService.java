package com.sehkmet.microservices.productservice.command.service.command;

import com.sehkmet.microservices.productservice.command.dto.command.request.CreateProductRequest;
import com.sehkmet.microservices.productservice.command.dto.command.response.CreateProductResponse;

public interface ProductCommandService {

    CreateProductResponse createProduct(CreateProductRequest createProductRequest);
    void deleteProduct(String id);
}
