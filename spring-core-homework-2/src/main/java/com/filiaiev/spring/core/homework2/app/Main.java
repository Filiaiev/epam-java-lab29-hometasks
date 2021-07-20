package com.filiaiev.spring.core.homework2.app;

import com.filiaiev.spring.core.homework2.config.Config1;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Config1.class);
        System.out.println("--------------------------- Beans");
        for(String n : context.getBeanDefinitionNames()){
            System.out.println(context.getBean(n));
        }
        System.out.println("---------------------------");
        context.close();
    }
}
