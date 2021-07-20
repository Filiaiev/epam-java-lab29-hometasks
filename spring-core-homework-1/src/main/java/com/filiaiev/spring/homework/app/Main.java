package com.filiaiev.spring.homework.app;

import com.filiaiev.spring.homework.beans4.JointBean;
import com.filiaiev.spring.homework.config.Config1;
import com.filiaiev.spring.homework.config.Config2;
import com.filiaiev.spring.homework.config.Config3;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(
                Config1.class, Config2.class, Config3.class);

        for (String name : context.getBeanDefinitionNames()){
            System.out.println(name);
        }

        System.out.println(context.getBean("collectorBean"));
        System.out.println(context.getBean(JointBean.class));
    }
}
