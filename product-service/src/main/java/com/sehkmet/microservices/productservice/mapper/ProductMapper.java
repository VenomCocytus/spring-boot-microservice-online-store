package com.sehkmet.microservices.productservice.mapper;

import com.sehkmet.microservices.productservice.model.Product;
import com.sehkmet.microservices.productservice.query.dto.GetProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    // Mapping the product Object of the database to the Object returned by the api endpoint
    GetProductResponse productToGetProductResponse(Product product);

}
