package com.sehkmet.core.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Utility class for internationalization (i18n) in a Spring application.
 * This class provides methods to retrieve localized messages based on keys.
 */
@Component
public class Translator {

    private static MessageSource messageSource;

    /**
     * Constructor that injects the MessageSource bean.
     *
     * @param messageSource the MessageSource bean used for resolving messages
     */
    @Autowired
    Translator(@Qualifier("messageSource") MessageSource messageSource) { Translator.messageSource = messageSource; }

    /**
     * Retrieves a localized message based on the given key.
     *
     * @param key the message key to look up (e.g., 'calculator.noRateSet')
     * @return the resolved message as a String
     * @see MessageSource#getMessage(String, Object[], Locale)
     */
    public static String toLocale(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, null, locale);
    }

    /**
     * Retrieves a localized message based on the given key and formats it with the provided arguments.
     *
     * @param key  the message key to look up
     * @param args optional arguments for formatting the message
     * @return the resolved and formatted message as a String
     * @see MessageSource#getMessage(String, Object[], Locale)
     */
    public static String toLocale(String key, Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(key, args, locale);
    }
}
