package com.filiaiev.spring.core.homework2.config;

import com.filiaiev.spring.core.homework2.beans.BeanB;
import com.filiaiev.spring.core.homework2.beans.BeanC;
import com.filiaiev.spring.core.homework2.beans.BeanD;
import com.filiaiev.spring.core.homework2.processor.BeanFactoryPostProcessorImpl;
import com.filiaiev.spring.core.homework2.processor.BeanPostProcessorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration
public class Config2 {

    @Bean(value = "beanB", initMethod = "initMethod", destroyMethod = "destroyMethod")
    @DependsOn("beanD")
    public BeanB getBeanB(){
        return new BeanB();
    }

    @Bean(value = "beanC", initMethod = "initMethod", destroyMethod = "destroyMethod")
    @DependsOn("beanB")
    public BeanC getBeanC(){
        return new BeanC();
    }

    @Bean(value = "beanD", initMethod = "initMethod", destroyMethod = "destroyMethod")
    public BeanD getBeanD(){
        return new BeanD();
    }

    @Bean("beanFactoryPostProcessorImpl")
    public BeanFactoryPostProcessorImpl getFactoryPostProcessorBean(){
        return new BeanFactoryPostProcessorImpl();
    }

    @Bean("beanPostProcessorImpl")
    public BeanPostProcessorImpl getBeanPostProcessor(){
        return new BeanPostProcessorImpl();
    }
}
