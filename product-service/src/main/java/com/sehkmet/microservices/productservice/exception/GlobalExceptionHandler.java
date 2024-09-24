package com.sehkmet.microservices.productservice.exception;

import com.sehkmet.microservices.productservice.component.Translator;
import com.sehkmet.microservices.productservice.exception.runtime.ProductNotFoundException;
import com.sehkmet.microservices.productservice.mapper.ErrorMapper;
import com.sehkmet.microservices.productservice.response.GenericResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.sehkmet.microservices.productservice.utils.Utils.translate;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ErrorMapper errorMapper;

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<GenericResponse<Object>> handleThrowableException(Throwable throwable){

        // Socket is closed, cannot return any response
        if(throwable instanceof ClientAbortException)
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(GenericResponse.error(
                        errorMapper.createErrorMap(throwable),
                        translate("exception.general-content")));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<GenericResponse<Object>> handleRuntimeException(String errorMessage) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(GenericResponse.error(
                        errorMapper.createErrorMap(errorMessage),
                        translate("exception.general-content")));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        Map<String, String> errorMessagesMap = new HashMap<>();

        exception.getBindingResult()
                .getAllErrors()
                .forEach((error) -> {
                    String fieldName;

                    try {
                        fieldName = ((FieldError) error).getField();
                    } catch (ClassCastException classCastException) {
                        fieldName = error.getObjectName();
                    }

                    String errorMessage = error.getDefaultMessage();
                    errorMessagesMap.put(fieldName, errorMessage);
                });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(GenericResponse.error(
                        errorMapper.createErrorMap(errorMessagesMap),
                        translate("exception.general-content")));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<GenericResponse<Object>> handleConstraintViolationException(ConstraintViolationException exception) {

        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        Map<String, String> errorMessagesMap = new HashMap<>();

        constraintViolations.forEach((constraintViolation -> {
            String fieldName =  String.format("%s", constraintViolation.getPropertyPath());
            String errorMessage = constraintViolation.getMessage();
            errorMessagesMap.put(fieldName, errorMessage);
        }));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(GenericResponse.error(
                        errorMapper.createErrorMap(errorMessagesMap),
                        translate("exception.general-content")));
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<GenericResponse<Object>> handleProductNotFoundException(ProductNotFoundException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(GenericResponse.error(
                        errorMapper.createErrorMap(
                                exception.getMessage()),
                        translate("exception.general-content")));
    }
}
