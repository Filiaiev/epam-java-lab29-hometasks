package com.filiaiev.spring.mvc1.repository;

import com.filiaiev.spring.mvc1.model.Employee;
import com.filiaiev.spring.mvc1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, EntityInfoRepository {

    @Override
    default Long getEntityIdByUser(User user) {
        return this.getEmployeeByUser(user).getId();
    }

    Employee getEmployeeByUser(User user);
}
