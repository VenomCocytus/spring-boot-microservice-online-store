package com.sehkmet.logging.config;

import ch.qos.logback.classic.ClassicConstants;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter2;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LokiLoggingConfigurer {

    @Bean
    public LokiLoggingConfigurer configureLokiLogging() throws JoranException {

        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        ContextInitializer contextInitializer = new ContextInitializer(loggerContext);
        System.setProperty(ClassicConstants.CONFIG_FILE_PROPERTY, "./logback-spring.xml");

        loggerContext.reset();
        contextInitializer.autoConfig();

        StatusPrinter2 statusPrinter = new StatusPrinter2();
        statusPrinter.printInCaseOfErrorsOrWarnings(loggerContext);

        return new LokiLoggingConfigurer();
    }
}
