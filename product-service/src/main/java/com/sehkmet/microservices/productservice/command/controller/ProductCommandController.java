package com.sehkmet.microservices.productservice.command.controller;

import com.sehkmet.microservices.productservice.command.dto.CreateProductRequestDTO;
import com.sehkmet.microservices.productservice.command.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductCommandController {

    private final ProductService productService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createProduct(@RequestBody CreateProductRequestDTO createProductRequest) {

        return productService.createProduct(createProductRequest);
    }
}
