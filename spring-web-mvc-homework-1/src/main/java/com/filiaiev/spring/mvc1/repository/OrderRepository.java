package com.filiaiev.spring.mvc1.repository;

import com.filiaiev.spring.mvc1.model.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class OrderRepository implements Repository<Order>{

    private final List<Order> orders = new ArrayList<>();

    @Override
    public Order getEntity(Integer key) {
        return orders.stream()
                .filter(v -> v.getId().equals(key))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Order> getAll() {
        return orders;
    }

    @Override
    public Order createEntity(Order entity) {
        orders.add(entity);
        return entity;
    }

    @Override
    public Order updateEntity(Integer id, Order entity) {
        boolean contains = orders.removeIf(v -> v.getId().equals(id));
        if(!contains) {
            throw new NoSuchElementException("Order with id '" + id + "' not present");
        }
        orders.add(entity);
        return entity;
    }

    @Override
    public void deleteEntity(Integer key) {

    }
}
