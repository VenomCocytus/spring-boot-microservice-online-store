package com.sehkmet.microservices.productservice.command.controller.query;

import com.sehkmet.core.common.GenericResponse;
import com.sehkmet.microservices.productservice.command.dto.query.response.GetProductResponse;
import com.sehkmet.microservices.productservice.command.service.query.ProductQueryService;
import com.sehkmet.microservices.productservice.exception.runtime.ProductNotFoundException;
import com.sehkmet.microservices.productservice.validation.annotation.ProductIdPathVariableExists;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.sehkmet.utils.utils.Utils.translate;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductQueryController {

    private final ProductQueryService productQueryService;

    @Operation(
            summary = "Retrieve all products",
            description = "This endpoint retrieves a list of all products available in the inventory."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Products retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GenericResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error"
            )
    })
    @GetMapping("/all")
    public ResponseEntity<GenericResponse<List<GetProductResponse>>> getAllProduct() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(
                        productQueryService.getAllProducts(),
                        translate("success.products-retrieved-successfully")));
    }

    @Operation(
            summary = "Retrieve product details",
            description = "This endpoint retrieves detailed information about a specific product identified by its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "302",
                    description = "Product found successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = GenericResponse.class)
                    )
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
