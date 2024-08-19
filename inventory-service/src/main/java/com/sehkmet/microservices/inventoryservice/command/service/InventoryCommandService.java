package com.sehkmet.microservices.inventoryservice.command.service;

public interface InventoryCommandService {

    public boolean isInStock(String skuCode, Integer quantity);

}
