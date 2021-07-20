package com.filiaiev.spring.core.homework2.processor;

import com.filiaiev.spring.core.homework2.beans.AbstractBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class BeanPostProcessorImpl implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof AbstractBean) {
            AbstractBean ab = ((AbstractBean) bean);
            if (ab.getName() == null || ab.getValue() < 0)
                System.out.println(beanName + " failed validation (name = " + ab.getName() + ", value = " + ab.getValue() + ")");
        }
        return bean;
    }

    @Override
    public String toString() {
        return "BeanPostProcessorImpl";
    }
}
