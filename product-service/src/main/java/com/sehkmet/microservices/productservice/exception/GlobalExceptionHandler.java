package com.sehkmet.microservices.productservice.exception;

import com.sehkmet.microservices.productservice.mapper.ErrorMapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleThrowableException(Throwable throwable){

        // Socket is closed, cannot return any response
        if(throwable instanceof ClientAbortException) return null;
        else return errorMapper.createErrorMap(throwable);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleRuntimeException(String errorMessage) {

        return errorMapper.createErrorMap(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
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

        return errorMapper.createErrorMap(stringBuilder.substring(0, stringBuilder.length()-1));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object handleConstraintViolationException(ConstraintViolationException exception) {

        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        StringBuilder stringBuilder = new StringBuilder();

        constraintViolations.forEach((constraintViolation -> {
            String fieldName =  String.format("%s", constraintViolation.getPropertyPath());
            String errorMessage = constraintViolation.getMessage();
            stringBuilder.append(String.format("%s: %s\n", fieldName, errorMessage));
        }));

        return errorMapper.createErrorMap(stringBuilder.substring(0, stringBuilder.length()-1));
    }

    @ExceptionHandler(FileNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object handleFileNotFoundException(FileNotFoundException exception) {

        return errorMapper.createErrorMap(exception.getMessage());
    }
}
