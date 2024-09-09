package com.sehkmet.microservices.productservice.command.controller;

import com.sehkmet.microservices.productservice.command.dto.CreateProductRequestRecord;
import com.sehkmet.microservices.productservice.command.service.ProductCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductCommandController {

    private final ProductCommandService productCommandService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createProduct(@RequestBody @Valid CreateProductRequestRecord createProductRequest) {

        return productCommandService.createProduct(createProductRequest);
    }
}
