package com.filiaiev.spring.mvc1.repository.impl;

import com.filiaiev.spring.mvc1.repository.OrderRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class OrderRepositoryImpl implements OrderRepository {

    private static List<String> sortFields = Arrays.asList("orderDate", "status", "cost");

    public static boolean isSortingAllowed(String field) {
        return field != null && sortFields.contains(field);
    }
}
