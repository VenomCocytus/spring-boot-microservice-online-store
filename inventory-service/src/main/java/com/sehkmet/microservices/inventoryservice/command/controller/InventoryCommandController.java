package com.sehkmet.microservices.inventoryservice.command.controller;

import com.sehkmet.core.common.GenericResponse;
import com.sehkmet.microservices.inventoryservice.command.dto.VerifyStockRequest;
import com.sehkmet.microservices.inventoryservice.command.service.InventoryCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.sehkmet.utils.utils.Utils.translate;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryCommandController {

    private final InventoryCommandService inventoryCommandService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
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
