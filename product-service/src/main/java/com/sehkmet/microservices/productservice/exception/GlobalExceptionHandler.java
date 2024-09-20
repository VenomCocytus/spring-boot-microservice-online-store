package com.sehkmet.microservices.productservice.exception;

import com.sehkmet.microservices.productservice.mapper.ErrorMapper;
import com.sehkmet.microservices.productservice.response.GenericResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.util.Set;

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
                        "exception.general-content"));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<GenericResponse<Object>> handleRuntimeException(String errorMessage) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(GenericResponse.error(
                        errorMapper.createErrorMap(errorMessage),
                        "exception.general-content"));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        StringBuilder stringBuilder = new StringBuilder();

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
                    stringBuilder.append(String.format("%s: %s\n", fieldName, errorMessage));
                });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(GenericResponse.error(
                        errorMapper.createErrorMap(
                                stringBuilder.substring(0, stringBuilder.length()-1)),
                        "exception.general-content"));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<GenericResponse<Object>> handleConstraintViolationException(ConstraintViolationException exception) {

        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        StringBuilder stringBuilder = new StringBuilder();

        constraintViolations.forEach((constraintViolation -> {
            String fieldName =  String.format("%s", constraintViolation.getPropertyPath());
            String errorMessage = constraintViolation.getMessage();
            stringBuilder.append(String.format("%s: %s\n", fieldName, errorMessage));
        }));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(GenericResponse.error(
                        errorMapper.createErrorMap(
                                stringBuilder.substring(0, stringBuilder.length()-1)),
                        "exception.general-content"));
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<GenericResponse<Object>> handleFileNotFoundException(FileNotFoundException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(GenericResponse.error(
                        errorMapper.createErrorMap(
                                exception.getMessage()),
                        "exception.file-not-found"));
    }
}
