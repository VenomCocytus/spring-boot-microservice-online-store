package com.sehkmet.microservices.productservice.utils;

import com.sehkmet.microservices.productservice.component.Translator;

import java.io.PrintWriter;
import java.io.StringWriter;

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
    public static String translate(String key, Object... args) {
        return Translator.toLocale(key, args);
    }

    public static String getStackTraceAsString(Exception exception) {

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        exception.printStackTrace(printWriter);

        return stringWriter.toString();
    }
}
