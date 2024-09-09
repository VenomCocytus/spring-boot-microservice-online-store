package com.sehkmet.microservices.productservice.query.controller;

import com.sehkmet.microservices.productservice.query.dto.GetProductResponse;
import com.sehkmet.microservices.productservice.query.service.ProductQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductQueryController {

    private final ProductQueryService productQueryService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<GetProductResponse> getAllProduct() {

        return productQueryService.getAllProducts();
    }

}
