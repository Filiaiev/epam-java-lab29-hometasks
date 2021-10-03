package com.filiaiev.spring.mvc1.util;

import com.filiaiev.spring.mvc1.model.Role;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class OrderRolesId {

    private Map<Role, Long> orderRoleIdMap;

    public OrderRolesId() {
        orderRoleIdMap = new HashMap<>();
    }

}
