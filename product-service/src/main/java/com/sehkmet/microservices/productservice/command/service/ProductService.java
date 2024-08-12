package com.sehkmet.microservices.productservice.command.service;

import com.sehkmet.microservices.productservice.command.dto.CreateProductRequestDTO;

public interface ProductService {

    public String createProduct(CreateProductRequestDTO createProductRequestDTO);
}
