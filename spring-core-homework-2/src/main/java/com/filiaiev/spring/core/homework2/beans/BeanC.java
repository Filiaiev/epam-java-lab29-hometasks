package com.filiaiev.spring.core.homework2.beans;

import com.filiaiev.spring.core.homework2.interfaces.PostProcessible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class BeanC extends AbstractBean implements PostProcessible {
    public BeanC(){
        System.out.println("BeanC constructor");
    }

    @Autowired
    public void setName(@Value("${c.name}") String name) {
        this.name = name;
    }

    @Autowired
    public void setValue(@Value("${c.value}") int value) {
        this.value = value;
    }
}
