package com.filiaiev.spring.mvc1.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Locale;
import java.util.ResourceBundle;

@Slf4j
public class Messages {

    @SneakyThrows
    public static String getMessage(String messageKey, Locale locale) {
        log.debug("Getting message key '{}' with locale {}", messageKey, locale);
        return ResourceBundle.getBundle("messages", locale)
                .getString(messageKey);
    }
}
