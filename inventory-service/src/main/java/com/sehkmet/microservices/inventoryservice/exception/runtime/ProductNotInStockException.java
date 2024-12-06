package com.sehkmet.microservices.inventoryservice.exception.runtime;

public class ProductNotInStockException extends RuntimeException{

    public ProductNotInStockException(String message) {
        super(message);
    }
}
