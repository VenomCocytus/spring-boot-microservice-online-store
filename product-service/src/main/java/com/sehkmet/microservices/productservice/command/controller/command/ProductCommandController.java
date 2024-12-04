package com.sehkmet.microservices.productservice.command.controller.command;

import com.sehkmet.core.common.GenericResponse;
import com.sehkmet.microservices.productservice.command.dto.command.request.CreateProductRequest;
import com.sehkmet.microservices.productservice.command.dto.command.response.CreateProductResponse;
import com.sehkmet.microservices.productservice.command.service.command.ProductCommandService;
import com.sehkmet.microservices.productservice.validation.annotation.ProductIdPathVariableExists;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.sehkmet.utils.utils.Utils.translate;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductCommandController {

    private final ProductCommandService productCommandService;

    @PostMapping("/create")
    public ResponseEntity<GenericResponse<CreateProductResponse>> createProduct(
            @Valid
            @RequestBody
            CreateProductRequest createProductRequest) {

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
