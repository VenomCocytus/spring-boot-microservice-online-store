package com.sehkmet.microservices.productservice.command.controller;

import com.sehkmet.microservices.productservice.command.dto.request.CreateProductRequestDTO;
import com.sehkmet.microservices.productservice.command.dto.response.CreateProductResponseDTO;
import com.sehkmet.microservices.productservice.command.service.ProductCommandService;
import com.sehkmet.microservices.productservice.response.GenericResponse;
import com.sehkmet.microservices.productservice.validation.annotation.ProductIdPathVariableExists;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.sehkmet.microservices.productservice.utils.Utils.translate;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductCommandController {

    private final ProductCommandService productCommandService;

    @PostMapping("/create")
    public ResponseEntity<GenericResponse<CreateProductResponseDTO>> createProduct(
            @Valid
            @RequestBody
            CreateProductRequestDTO createProductRequest) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GenericResponse.success(
                        productCommandService.createProduct(createProductRequest),
                        translate("success.product-created-successfully")
                ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<?>> deleteProduct(
            @ProductIdPathVariableExists
            @PathVariable
            String id) {

        productCommandService.deleteProduct(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(GenericResponse.success(
                        translate("success.product-deleted-successfully")
                ));
    }
}
