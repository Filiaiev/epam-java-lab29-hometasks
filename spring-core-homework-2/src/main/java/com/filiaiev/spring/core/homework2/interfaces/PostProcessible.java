package com.filiaiev.spring.core.homework2.interfaces;

public interface PostProcessible {
    default void initMethod(){
        System.out.println(this.getClass().getSimpleName() + " init method");
    };

    default void destroyMethod(){
        System.out.println(this.getClass().getSimpleName() + " destroy method");
    };
}
