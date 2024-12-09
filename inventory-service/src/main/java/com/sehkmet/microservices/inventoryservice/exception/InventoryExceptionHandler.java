package com.sehkmet.microservices.inventoryservice.exception;

import com.sehkmet.core.common.GenericResponse;
import com.sehkmet.core.component.ErrorBuilder;
import com.sehkmet.microservices.inventoryservice.exception.runtime.ProductNotInStockException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.sehkmet.utils.utils.Utils.translate;

@RestControllerAdvice
public class InventoryExceptionHandler{

    @ExceptionHandler(ProductNotInStockException.class)
    public ResponseEntity<GenericResponse<Object>> handleProductNotInStockException(ProductNotInStockException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(GenericResponse.error(exception.getMessage()));
    }
}
