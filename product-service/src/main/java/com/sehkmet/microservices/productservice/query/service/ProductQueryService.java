package com.sehkmet.microservices.productservice.query.service;

import com.sehkmet.microservices.productservice.query.dto.ProductResponse;

import java.util.List;

public interface ProductQueryService {

    public List<ProductResponse> getAllProducts();
}
