package com.filiaiev.spring.mvc1.repository;

import com.filiaiev.spring.mvc1.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

//    private final List<Order> orders = new ArrayList<>();
//
//    @Override
//    public Order getEntity(int key) {
//        return orders.stream()
//                .filter(v -> v.getId().equals(key))
//                .findFirst()
//                .orElseThrow(NoSuchElementException::new);
//    }
//
//    @Override
//    public List<Order> getAll() {
//        return orders;
//    }
//
//    @Override
//    public Order createEntity(Order entity) {
//        orders.add(entity);
//        return entity;
//    }
//
//    @Override
//    public Order updateEntity(int id, Order entity) {
//        // TODO: Rework
//        Optional<Order> optional = orders.stream()
//                .filter(v -> v.getId().equals(id))
//                .findFirst();
//
//        if(!optional.isPresent()) {
//            throw new NoSuchElementException("Order with id '" + id + "' not present");
//        }
//
//        Order order = optional.get();
//        BeanUtils.copyProperties(entity, order, BeanUtilImpl.getNullFields(entity));
//        orders.add(order);
//        return order;
//    }
//
//    @Override
//    public void deleteEntity(int key) {
//
//    }
}
