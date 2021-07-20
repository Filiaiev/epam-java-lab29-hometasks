package com.filiaiev.spring.homework.beans4;

import com.filiaiev.spring.homework.interfaces.Beanterface;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component("beanH")
@Order(1)
public class BeanH implements Beanterface {
    @Override
    public String toString() {
        return "BeanH";
    }
}
