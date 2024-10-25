package com.sehkmet.microservices.productservice.mapper;

import com.sehkmet.microservices.productservice.command.dto.request.CreateProductRequest;
import com.sehkmet.microservices.productservice.command.dto.response.CreateProductResponse;
import com.sehkmet.microservices.productservice.model.Product;
import com.sehkmet.microservices.productservice.query.dto.GetProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    GetProductResponse mapToGetProductResponse(Product product);
    CreateProductResponse mapToCreateProductResponse(Product product);
    Product mapToProduct(CreateProductRequest createProductRequest);

}
