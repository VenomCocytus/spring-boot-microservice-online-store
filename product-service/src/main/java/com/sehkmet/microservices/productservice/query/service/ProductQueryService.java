package com.sehkmet.microservices.productservice.query.service;

import com.sehkmet.microservices.productservice.query.dto.GetProductResponse;

import java.util.List;

public interface ProductQueryService {

    public GetProductResponse getProductDetails(String productId);
    public List<GetProductResponse> getAllProducts();
}
