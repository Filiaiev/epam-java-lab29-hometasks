package com.filiaiev.spring.core.homework2.interfaces;

public interface LifecycleControllable {
    default void initMethod(){
        System.out.println(this.getClass().getSimpleName() + " init method");
    };

    default void destroyMethod(){
        System.out.println(this.getClass().getSimpleName() + " destroy method");
    };
}
