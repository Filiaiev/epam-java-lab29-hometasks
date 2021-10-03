package com.filiaiev.spring.mvc1.repository;

import com.filiaiev.spring.mvc1.model.*;
import com.filiaiev.spring.mvc1.util.SecurityUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    default Page<Order> findAllImpl(Pageable pageable) {
        UserDetailsImpl userDetails = SecurityUtil.getUserDetails(SecurityContextHolder.getContext().getAuthentication());

        switch (userDetails.getRole()) {
            case ROLE_CLIENT:
                Client client = new Client();
                client.setId(userDetails.getRoleEntityIdMap().get(Role.ROLE_CLIENT));
                return this.findOrdersByClient(pageable, client);
            case ROLE_MANAGER:
                return this.findAll(pageable);
            case ROLE_REPAIRER:
                Employee employee = new Employee();
                employee.setId(userDetails.getRoleEntityIdMap().get(Role.ROLE_REPAIRER));
                return this.findOrderByEmployee(pageable, employee);
            default:
                return null;
        }
    }

    Page<Order> findOrdersByClient(Pageable p, Client client);

    Page<Order> findOrderByEmployee(Pageable p, Employee employee);
}
