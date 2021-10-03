package com.filiaiev.spring.mvc1.repository;

import com.filiaiev.spring.mvc1.model.Client;
import com.filiaiev.spring.mvc1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>, EntityInfoRepository {

    @Override
    default Long getEntityIdByUser(User user) {
        return this.getClientByUser(user).getId();
    }

    Client getClientByUser(User user);
}
