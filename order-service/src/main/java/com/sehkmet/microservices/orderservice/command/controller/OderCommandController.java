package com.sehkmet.microservices.orderservice.command.controller;

import com.sehkmet.microservices.orderservice.command.dto.PlaceOrderRequestRecord;
import com.sehkmet.microservices.orderservice.command.service.OrderCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OderCommandController {

    private final OrderCommandService orderCommandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody @Valid PlaceOrderRequestRecord placeOrderRequestRecord) {
        return orderCommandService.placeOrder(placeOrderRequestRecord);
    }
}
