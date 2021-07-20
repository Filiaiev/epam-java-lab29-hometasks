package com.filiaiev.spring.core.homework2.beans;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class BeanF extends AbstractBean {
    public BeanF() {
        System.out.println("BeanF constructor");
    }
}
