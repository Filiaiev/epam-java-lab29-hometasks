package com.filiaiev.spring.mvc1.util;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.FeatureDescriptor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class BeanUtilImpl {

    private BeanUtilImpl() {}

    public static String[] getNullFields(Object o) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(o);

        return Arrays.stream(beanWrapper.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(f -> beanWrapper.getPropertyValue(f) == null)
                .toArray(String[]::new);
    }
}
