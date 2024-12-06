package com.sehkmet.microservices.inventoryservice.command.controller;

import com.sehkmet.microservices.inventoryservice.command.dto.VerifyStockRequest;
import com.sehkmet.microservices.inventoryservice.command.service.InventoryCommandService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryCommandController {

    private final InventoryCommandService inventoryCommandService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(
            @Valid
            @RequestBody
            VerifyStockRequest verifyStockRequest) {
        return inventoryCommandService.isInStock(verifyStockRequest);
    }
}
