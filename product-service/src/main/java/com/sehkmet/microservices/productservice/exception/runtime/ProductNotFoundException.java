package com.sehkmet.microservices.productservice.exception.runtime;

import java.io.FileNotFoundException;

public class ProductNotFoundException extends FileNotFoundException {

    public ProductNotFoundException(String message) {
        super(message);
    }
}
