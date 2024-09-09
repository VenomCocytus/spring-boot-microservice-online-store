package com.sehkmet.microservices.productservice.common.request;

import com.sehkmet.microservices.productservice.command.dto.CreateProductRequestRecord;

import java.math.BigDecimal;

public class CommandRequests {

    public static CreateProductRequestRecord getProductRequest() {
        return new CreateProductRequestRecord(
                "Pokedex",
                "A tool register data about encountered pokemon",
                BigDecimal.valueOf(2000)
        );
    }
}
