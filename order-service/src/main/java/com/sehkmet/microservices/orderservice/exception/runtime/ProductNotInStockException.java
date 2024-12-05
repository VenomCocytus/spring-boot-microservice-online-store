package com.sehkmet.microservices.orderservice.exception.runtime;

public class ProductNotInStockException extends RuntimeException{

    public ProductNotInStockException(String message) {
        super(message);
    }
}
