package com.sehkmet.microservices.productservice.common.request;

import com.sehkmet.microservices.productservice.command.dto.request.CreateProductRequestDTO;

import java.math.BigDecimal;

public class CommandRequests {

    public static CreateProductRequestDTO getProductRequest() {
        return new CreateProductRequestDTO(
                "Pokedex",
                "A tool register data about encountered pokemon",
                BigDecimal.valueOf(2000)
        );
    }
}
