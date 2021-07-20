package com.filiaiev.spring.homework.config;

import com.filiaiev.spring.homework.beans3.BeanD;
import com.filiaiev.spring.homework.beans3.BeanF;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScans({
        @ComponentScan(basePackages = "com.filiaiev.spring.homework.beans2"),
        @ComponentScan(basePackages = "com.filiaiev.spring.homework.beans3",
            useDefaultFilters = false,
            includeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {BeanD.class, BeanF.class})})
})
public class Config2 {
}
