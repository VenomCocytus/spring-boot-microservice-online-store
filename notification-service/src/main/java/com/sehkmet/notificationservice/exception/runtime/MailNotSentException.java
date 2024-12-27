package com.sehkmet.notificationservice.exception.runtime;

public class MailNotSentException extends RuntimeException{

    public MailNotSentException(String message) {
        super(message);
    }
}
