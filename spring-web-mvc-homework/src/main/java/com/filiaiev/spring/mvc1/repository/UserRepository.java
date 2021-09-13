package com.filiaiev.spring.mvc1.repository;

import com.filiaiev.spring.mvc1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmailOrLogin(String email, String login);

    Optional<User> getUserByLogin(String login);
}
