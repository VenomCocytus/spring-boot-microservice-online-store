package com.sehkmet.microservices.productservice.command.service.query;

import com.sehkmet.microservices.productservice.exception.runtime.ProductNotFoundException;
import com.sehkmet.microservices.productservice.command.dto.query.response.GetProductResponse;

import java.util.List;

public interface ProductQueryService {

    GetProductResponse getProductDetails(String productId) throws ProductNotFoundException;
    List<GetProductResponse> getAllProducts();
}
