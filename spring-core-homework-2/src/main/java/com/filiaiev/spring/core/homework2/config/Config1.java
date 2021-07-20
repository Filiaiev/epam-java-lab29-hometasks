package com.filiaiev.spring.core.homework2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@Import(Config2.class)
@ComponentScan(value = {"com.filiaiev.spring.core.homework2.beans"})
public class Config1 {
}

