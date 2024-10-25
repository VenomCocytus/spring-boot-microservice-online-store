package com.sehkmet.microservices.inventoryservice.command.service;

public interface InventoryCommandService {

    boolean isInStock(String skuCode, Integer quantity);

}
