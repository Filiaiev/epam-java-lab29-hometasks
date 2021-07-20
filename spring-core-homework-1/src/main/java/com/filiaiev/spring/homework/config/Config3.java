package com.filiaiev.spring.homework.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(value = {"com.filiaiev.spring.homework.injectors",
                        "com.filiaiev.spring.homework.other",
                        "com.filiaiev.spring.homework.beans4"})
public class Config3 {
}
