package com.sehkmet.microservices.productservice.command.service;

import com.sehkmet.microservices.productservice.command.dto.CreateProductRequestRecord;

public interface ProductCommandService {

    public String createProduct(CreateProductRequestRecord createProductRequestRecord);
}
