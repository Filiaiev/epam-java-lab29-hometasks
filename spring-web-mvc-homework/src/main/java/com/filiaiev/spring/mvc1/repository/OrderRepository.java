package com.filiaiev.spring.mvc1.repository;

import com.filiaiev.spring.mvc1.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
