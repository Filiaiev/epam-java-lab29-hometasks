package com.filiaiev.spring.core.homework2.beans;

import com.filiaiev.spring.core.homework2.interfaces.PostProcessible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class BeanB extends AbstractBean implements PostProcessible {
    public BeanB(){
        System.out.println("BeanB constructor");
    }

    @Autowired
    public void setName(@Value("${b.name}") String name) {
        this.name = name;
    }

    @Autowired
    public void setValue(@Value("${b.value}") int value) {
        this.value = value;
    }

    public void secondInitMethod(){
        System.out.println(this.getClass().getSimpleName() + " changed init method");
    }
}
