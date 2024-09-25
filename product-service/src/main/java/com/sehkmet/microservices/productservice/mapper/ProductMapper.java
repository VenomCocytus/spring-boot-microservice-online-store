package com.sehkmet.microservices.productservice.mapper;

import com.sehkmet.microservices.productservice.command.dto.request.CreateProductRequestDTO;
import com.sehkmet.microservices.productservice.command.dto.response.CreateProductResponseDTO;
import com.sehkmet.microservices.productservice.model.Product;
import com.sehkmet.microservices.productservice.query.dto.GetProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    GetProductResponse mapToGetProductResponse(Product product);
    CreateProductResponseDTO mapToCreateProductResponse(Product product);
    Product mapToProduct(CreateProductRequestDTO createProductRequestDTO);

}
