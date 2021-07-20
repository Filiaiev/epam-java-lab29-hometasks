package com.filiaiev.spring.homework.beans4;

import com.filiaiev.spring.homework.interfaces.Beanterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CollectorBean {
    private Beanterface[] beanterfaces;

    @Autowired
    public CollectorBean(Beanterface[] beanterfaces){
        this.beanterfaces = beanterfaces;
    }

    @Override
    public String toString() {
        return "CollectorBean = " + Arrays.toString(beanterfaces);
    }
}
