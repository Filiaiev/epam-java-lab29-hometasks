package com.filiaiev.spring.homework.injectors;

import com.filiaiev.spring.homework.other.OtherBeanA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InjectConstructor {
    private OtherBeanA otherBeanA;

    @Autowired
    public InjectConstructor(OtherBeanA otherBeanA){
        this.otherBeanA = otherBeanA;
    }

    @Override
    public String toString() {
        return "InjectConstructor otherBeanA = " + otherBeanA;
    }
}
