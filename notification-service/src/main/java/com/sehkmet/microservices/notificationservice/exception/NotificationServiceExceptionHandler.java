package com.sehkmet.microservices.notificationservice.exception;

import com.sehkmet.core.common.GenericResponse;
import com.sehkmet.core.component.ErrorBuilder;
import com.sehkmet.microservices.notificationservice.exception.runtime.MailNotSentException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.sehkmet.utils.utils.Utils.translate;

@RestControllerAdvice
@RequiredArgsConstructor
public class NotificationServiceExceptionHandler {

    private final ErrorBuilder errorBuilder;

    @ExceptionHandler(MailNotSentException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ResponseEntity<GenericResponse<Object>> handleMailNotSentException(MailNotSentException exception) {

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(GenericResponse.error(
                        errorBuilder.createErrorMap(exception.getMessage()),
                        translate("exception.general-content")));
    }

    @ExceptionHandler(MailException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ResponseEntity<GenericResponse<Object>> handleMailException(MailNotSentException exception) {

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(GenericResponse.error(
                        errorBuilder.createErrorMap(exception.getMessage()),
                        translate("exception.general-content")));
    }
}
