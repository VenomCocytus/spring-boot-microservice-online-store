package com.sehkmet.microservices.productservice.exception.runtime;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String message) {
        super(message);
    }
}
