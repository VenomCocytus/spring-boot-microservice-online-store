package com.sehkmet.microservices.productservice.command.service;

import com.sehkmet.microservices.productservice.command.dto.request.CreateProductRequestDTO;
import com.sehkmet.microservices.productservice.command.dto.response.CreateProductResponseDTO;

public interface ProductCommandService {

    CreateProductResponseDTO createProduct(CreateProductRequestDTO createProductRequestDTO);
}
