package com.sehkmet.microservices.productservice.exception;

import com.sehkmet.core.common.GenericResponse;
import com.sehkmet.core.component.ErrorBuilder;
import com.sehkmet.microservices.productservice.exception.runtime.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.sehkmet.utils.utils.Utils.translate;

@RestControllerAdvice
@RequiredArgsConstructor
public class ProductServiceExceptionHandler {

    private final ErrorBuilder errorBuilder;

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<GenericResponse<Object>> handleProductNotFoundException(ProductNotFoundException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(GenericResponse.error(
                        errorBuilder.createErrorMap(exception.getMessage()),
                        translate("exception.general-content")));
    }
}
