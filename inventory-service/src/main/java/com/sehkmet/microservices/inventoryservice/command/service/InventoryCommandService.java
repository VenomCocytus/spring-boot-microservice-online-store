package com.sehkmet.microservices.inventoryservice.command.service;

import com.sehkmet.microservices.inventoryservice.command.dto.VerifyStockRequest;

public interface InventoryCommandService {

    void isInStock(VerifyStockRequest verifyStockRequest);

}
