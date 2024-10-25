package com.sehkmet.microservices.productservice.common.request;

import com.sehkmet.microservices.productservice.command.dto.request.CreateProductRequest;

import java.math.BigDecimal;

public class CommandRequests {

    public static CreateProductRequest getProductRequest() {
        return new CreateProductRequest(
                "Pokedex",
                "A tool register data about encountered pokemon",
                BigDecimal.valueOf(2000)
        );
    }
}
