package com.filiaiev.spring.core.homework2.beans;

import com.filiaiev.spring.core.homework2.interfaces.LifecycleControllable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class BeanD extends AbstractBean implements LifecycleControllable {

    public BeanD(){
        System.out.println("BeanD constructor");
    }

    @Autowired
    public void setName(@Value("${d.name}") String name) {
        this.name = name;
    }

    @Autowired
    public void setValue(@Value("${d.value}") int value) {
        this.value = value;
    }

    @Override
    public void initMethod() {
        System.out.println(this.getClass().getSimpleName() + " init method");
    }

    @Override
    public void destroyMethod() {
        System.out.println(this.getClass().getSimpleName() + " destroy method");
    }
}
