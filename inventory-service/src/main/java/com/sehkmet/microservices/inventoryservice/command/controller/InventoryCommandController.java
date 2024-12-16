package com.sehkmet.microservices.inventoryservice.command.controller;

import com.sehkmet.core.common.GenericResponse;
import com.sehkmet.microservices.inventoryservice.command.dto.VerifyStockRequest;
import com.sehkmet.microservices.inventoryservice.command.service.InventoryCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import static com.sehkmet.utils.utils.Utils.translate;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryCommandController {

    private final InventoryCommandService inventoryCommandService;

    @Operation(
            summary = "Check product stock availability",
            description = "This endpoint checks if a specific product is in stock based on the provided details."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product is in stock",
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
                            schema = @Schema(implementation = ConstraintViolation.class)
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
    @PostMapping
    public ResponseEntity<GenericResponse<?>> isInStock(
            @Valid
            @RequestBody
            VerifyStockRequest verifyStockRequest) {
        this.inventoryCommandService.isInStock(verifyStockRequest);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GenericResponse.success(
                        translate("success.inventory-product-in-stock")
                ))
        ;
    }
}
