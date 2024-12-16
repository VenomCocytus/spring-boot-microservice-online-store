package com.sehkmet.microservices.inventoryservice.exception;

import com.sehkmet.core.common.GenericResponse;
import com.sehkmet.microservices.inventoryservice.exception.runtime.ProductNotInStockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InventoryExceptionHandler{

    @ExceptionHandler(ProductNotInStockException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<GenericResponse<Object>> handleProductNotInStockException(ProductNotInStockException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(GenericResponse.error(exception.getMessage()));
    }
}
