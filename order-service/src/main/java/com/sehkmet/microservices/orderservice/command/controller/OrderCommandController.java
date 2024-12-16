package com.sehkmet.microservices.orderservice.command.controller;

import com.sehkmet.core.common.GenericResponse;
import com.sehkmet.microservices.orderservice.command.dto.PlaceOrderRequest;
import com.sehkmet.microservices.orderservice.command.dto.PlaceOrderResponse;
import com.sehkmet.microservices.orderservice.command.service.OrderCommandService;
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
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderCommandController {

    private final OrderCommandService orderCommandService;

    @Operation(
            summary = "Place a new order",
            description = "This endpoint allows you to place a new order by providing the necessary order details."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Order created successfully",
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
    public ResponseEntity<GenericResponse<PlaceOrderResponse>> placeOrder(
            @RequestBody
            @Valid
            PlaceOrderRequest placeOrderRequest) {
        this.orderCommandService.placeOrder(placeOrderRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GenericResponse.success(
                        this.orderCommandService.placeOrder(placeOrderRequest),
                        translate("success.order-created-successfully")
                ));
    }
}
