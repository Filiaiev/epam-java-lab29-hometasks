package com.filiaiev.spring.mvc1.repository;

import com.filiaiev.spring.mvc1.model.User;

public interface EntityInfoRepository {

    Long getEntityIdByUser(User user);
}
