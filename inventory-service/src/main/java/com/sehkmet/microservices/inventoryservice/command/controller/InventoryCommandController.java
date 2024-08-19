package com.sehkmet.microservices.inventoryservice.command.controller;

import com.sehkmet.microservices.inventoryservice.command.service.InventoryCommandService;
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
    public boolean isInStock(@RequestParam String skuCode, @RequestParam Integer quantity) {
        return inventoryCommandService.isInStock(skuCode, quantity);
    }
}
