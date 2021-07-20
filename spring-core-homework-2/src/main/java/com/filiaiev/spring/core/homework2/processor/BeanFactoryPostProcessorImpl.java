package com.filiaiev.spring.core.homework2.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class BeanFactoryPostProcessorImpl implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("BeanFactoryPostProcessorImpl changing beanB init method...");
        configurableListableBeanFactory.getBeanDefinition("beanB")
                .setInitMethodName("secondInitMethod");
    }

    @Override
    public String toString() {
        return "BeanFactoryPostProcessorImpl";
    }
}
