package com.sehkmet.microservices.productservice.query.controller;

import com.sehkmet.microservices.productservice.query.dto.GetProductResponse;
import com.sehkmet.microservices.productservice.query.service.ProductQueryService;
import com.sehkmet.microservices.productservice.validation.annotation.ProductIdPathVariableExists;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.FOUND)
    public GetProductResponse getProductDetails(
            @ProductIdPathVariableExists
            @PathVariable
            String productId) {

        return productQueryService.getProductDetails(productId);
    }

}
