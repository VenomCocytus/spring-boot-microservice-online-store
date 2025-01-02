package com.sehkmet.microservices.notificationservice.exception.runtime;

public class MailNotSentException extends RuntimeException{

    public MailNotSentException(String message) {
        super(message);
    }
}
