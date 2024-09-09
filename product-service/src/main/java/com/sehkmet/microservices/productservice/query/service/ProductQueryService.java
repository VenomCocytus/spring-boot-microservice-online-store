package com.sehkmet.microservices.productservice.query.service;

import com.sehkmet.microservices.productservice.query.dto.GetProductResponse;

import java.util.List;

public interface ProductQueryService {

    GetProductResponse getProductDetails(String productId);
    List<GetProductResponse> getAllProducts();
}
