package com.filiaiev.spring.mvc1.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

@Configuration
public class PersistenceConfig {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Bean(name = "myEntityManager")
    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
