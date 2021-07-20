package com.filiaiev.spring.homework.injectors;

import com.filiaiev.spring.homework.other.OtherBeanC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class InjectField {
    @Autowired
    @Qualifier("qualifiedOtherBeanC")
    private OtherBeanC otherBeanC;

    @Override
    public String toString() {
        return "InjectField otherBeanC = " + otherBeanC;
    }
}
