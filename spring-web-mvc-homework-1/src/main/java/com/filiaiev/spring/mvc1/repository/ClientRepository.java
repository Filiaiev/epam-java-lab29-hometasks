package com.filiaiev.spring.mvc1.repository;

import com.filiaiev.spring.mvc1.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
