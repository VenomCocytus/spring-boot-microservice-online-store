package com.sehkmet.microservices.productservice.command.controller.command;

import com.sehkmet.core.common.GenericResponse;
import com.sehkmet.microservices.productservice.command.dto.command.request.CreateProductRequest;
import com.sehkmet.microservices.productservice.command.dto.command.response.CreateProductResponse;
import com.sehkmet.microservices.productservice.command.service.command.ProductCommandService;
import com.sehkmet.microservices.productservice.exception.runtime.ProductNotFoundException;
import com.sehkmet.microservices.productservice.validation.annotation.ProductIdPathVariableExists;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import static com.sehkmet.utils.utils.Utils.translate;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductCommandController {

    private final ProductCommandService productCommandService;

    @Operation(
            summary = "Create a new product",
            description = "This endpoint allows you to create a new product in the inventory."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Product created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GenericResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input data",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ConstraintViolationException.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
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

    @Operation(
            summary = "Delete a product",
            description = "This endpoint allows you to delete a product from the inventory by its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Product deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductNotFoundException.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
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
