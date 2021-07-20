package com.filiaiev.spring.homework.beans4;

import com.filiaiev.spring.homework.interfaces.Beanterface;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component("beanI")
@Order(2)
public class BeanI implements Beanterface {
    @Override
    public String toString() {
        return "BeanI";
    }
}
