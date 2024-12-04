package com.sehkmet.microservices.productservice.command.controller.query;

import com.sehkmet.microservices.productservice.exception.runtime.ProductNotFoundException;
import com.sehkmet.microservices.productservice.command.dto.query.response.GetProductResponse;
import com.sehkmet.microservices.productservice.command.service.query.ProductQueryService;
import com.sehkmet.microservices.productservice.response.GenericResponse;
import com.sehkmet.microservices.productservice.validation.annotation.ProductIdPathVariableExists;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.sehkmet.microservices.productservice.utils.Utils.translate;
@Validated
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
                        translate("success.products-retrieved-successfully")));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<GenericResponse<GetProductResponse>> getProductDetails(
            @PathVariable
            @ProductIdPathVariableExists
            String productId) throws ProductNotFoundException {

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(GenericResponse.success(
                        productQueryService.getProductDetails(productId),
                        translate("success.product-retrieved-successfully")));
    }

}
