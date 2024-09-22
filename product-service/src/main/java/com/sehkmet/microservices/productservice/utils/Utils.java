package com.sehkmet.microservices.productservice.utils;

import com.sehkmet.microservices.productservice.component.Translator;

public abstract class Utils {

    /**
     * Util to return a custom message based on his key
     *
     * @param key The key in the properties file
     * @return The message corresponding to the key
     */
    public static String translate(String key) {
        return Translator.toLocale(key);
    }
}
