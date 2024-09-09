package com.sehkmet.microservices.productservice.mapper;

import com.sehkmet.microservices.productservice.model.Product;
import com.sehkmet.microservices.productservice.query.dto.GetProductResponse;
import lombok.Getter;

@Getter
public class ProductMapper {

    public GetProductResponse mapProductToGetProductResponse(Product product){
        return new GetProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }
}
