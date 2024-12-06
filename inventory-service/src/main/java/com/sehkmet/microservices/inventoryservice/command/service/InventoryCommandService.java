package com.sehkmet.microservices.inventoryservice.command.service;

import com.sehkmet.microservices.inventoryservice.command.dto.VerifyStockRequest;

public interface InventoryCommandService {

    boolean isInStock(VerifyStockRequest verifyStockRequest);

}
