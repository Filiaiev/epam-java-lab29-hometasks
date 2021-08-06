package com.filiaiev.spring.mvc1.repository;

import com.filiaiev.spring.mvc1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByEmailOrLogin(String email, String login);
}
