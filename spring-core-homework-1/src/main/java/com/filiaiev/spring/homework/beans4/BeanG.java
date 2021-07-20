package com.filiaiev.spring.homework.beans4;

import com.filiaiev.spring.homework.interfaces.Beanterface;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class BeanG implements Beanterface {
    @Override
    public String toString() {
        return "BeanG";
    }
}
