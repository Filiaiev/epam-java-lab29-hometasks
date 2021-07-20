package com.filiaiev.spring.homework.injectors;

import com.filiaiev.spring.homework.other.OtherBeanB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InjectSetter {
    private OtherBeanB otherBeanB;

    @Autowired
    public void setOtherBeanB(OtherBeanB otherBeanB){
        this.otherBeanB = otherBeanB;
    }

    @Override
    public String toString() {
        return "InjectSetter otherBeanB = " + otherBeanB;
    }
}
