package com.sehkmet.microservices.orderservice.command.controller;

import com.sehkmet.core.common.GenericResponse;
import com.sehkmet.microservices.orderservice.command.dto.PlaceOrderRequest;
import com.sehkmet.microservices.orderservice.command.service.OrderCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderCommandController {

    private final OrderCommandService orderCommandService;

    @PostMapping
    public ResponseEntity<GenericResponse<String>> placeOrder(
            @RequestBody
            @Valid
            PlaceOrderRequest placeOrderRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GenericResponse.success(
                        orderCommandService.placeOrder(placeOrderRequest),
                        translate("")
                ));
    }
}
