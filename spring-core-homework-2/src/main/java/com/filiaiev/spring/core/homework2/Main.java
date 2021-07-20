package com.filiaiev.spring.core.homework2;

import com.filiaiev.spring.core.homework2.beans.*;
import com.filiaiev.spring.core.homework2.config.Config1;
import com.filiaiev.spring.core.homework2.config.Config2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Config1.class);
        System.out.println("--------------------------- Beans");
        for(String n : context.getBeanDefinitionNames()){
//            System.out.println(n);
            System.out.println(context.getBean(n));
        }
        System.out.println("---------------------------");

//        context.getBean("beanC");

//        System.out.println(context.getBean(BeanA.class));
//        System.out.println(context.getBean(BeanB.class));
//        System.out.println(context.getBean(BeanC.class));
//        System.out.println(context.getBean(BeanD.class));
//        System.out.println(context.getBean(BeanE.class));
//        System.out.println(context.getBean(BeanF.class));
        context.close();
    }
}
