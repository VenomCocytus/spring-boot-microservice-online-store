package com.sehkmet.microservices.productservice.query.controller;

import com.sehkmet.microservices.productservice.query.dto.GetProductResponse;
import com.sehkmet.microservices.productservice.query.service.ProductQueryService;
import com.sehkmet.microservices.productservice.response.GenericResponse;
import com.sehkmet.microservices.productservice.validation.annotation.ProductIdPathVariableExists;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductQueryController {

    private final ProductQueryService productQueryService;

    @GetMapping("/all")
    public ResponseEntity<GenericResponse<List<GetProductResponse>>> getAllProduct() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(
                        productQueryService.getAllProducts(),
                        "success.products-retrieved-successfully"));
    }

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<GenericResponse<GetProductResponse>> getProductDetails(
            @ProductIdPathVariableExists
            @PathVariable
            String productId) {

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(GenericResponse.success(
                        productQueryService.getProductDetails(productId),
                        "success.product-retrieved-successfully"));
    }

}
