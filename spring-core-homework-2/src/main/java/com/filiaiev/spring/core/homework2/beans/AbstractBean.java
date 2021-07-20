package com.filiaiev.spring.core.homework2.beans;

public abstract class AbstractBean {
    protected String name;
    protected int value;

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ": [name = " + name + ", value = " + value + "]";
    }
}
