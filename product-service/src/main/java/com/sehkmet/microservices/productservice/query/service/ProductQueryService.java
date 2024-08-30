package com.sehkmet.microservices.productservice.query.service;

import com.sehkmet.microservices.productservice.query.dto.CreateProductResponse;

import java.util.List;

public interface ProductQueryService {

    public List<CreateProductResponse> getAllProducts();
}
