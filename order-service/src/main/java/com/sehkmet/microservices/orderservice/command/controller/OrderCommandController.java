package com.sehkmet.microservices.orderservice.command.controller;

import com.sehkmet.core.common.GenericResponse;
import com.sehkmet.microservices.orderservice.command.dto.PlaceOrderRequest;
import com.sehkmet.microservices.orderservice.command.dto.PlaceOrderResponse;
import com.sehkmet.microservices.orderservice.command.service.OrderCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.sehkmet.utils.utils.Utils.translate;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderCommandController {

    private final OrderCommandService orderCommandService;

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
