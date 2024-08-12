package com.sehkmet.microservices.productservice.command.service;

import com.sehkmet.microservices.productservice.command.dto.CreateProductRequestDTO;
import com.sehkmet.microservices.productservice.model.Product;

public interface ProductService {

    public String createProduct(CreateProductRequestDTO createProductRequestDTO);
}
