package com.filiaiev.spring.mvc1.repository;

import java.util.List;

public interface Repository<T> {

    T getEntity(Integer key);

    T createEntity(T entity);

    T updateEntity(Integer id, T entity);

    List<T> getAll();

    void deleteEntity(Integer key);
}
