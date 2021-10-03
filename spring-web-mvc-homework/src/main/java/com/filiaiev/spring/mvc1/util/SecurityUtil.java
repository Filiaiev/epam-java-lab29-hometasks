package com.filiaiev.spring.mvc1.util;

import com.filiaiev.spring.mvc1.model.Role;
import com.filiaiev.spring.mvc1.model.UserDetailsImpl;
import com.filiaiev.spring.mvc1.repository.ClientRepository;
import com.filiaiev.spring.mvc1.repository.EmployeeRepository;
import com.filiaiev.spring.mvc1.repository.EntityInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SecurityUtil {

    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;

    private static final Map<Role, EntityInfoRepository> roleRepositoryMap = new HashMap<>();

    @Autowired
    private SecurityUtil(ClientRepository clientRepository, EmployeeRepository employeeRepository) {
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;

        roleRepositoryMap.put(Role.ROLE_CLIENT, clientRepository);
        roleRepositoryMap.put(Role.ROLE_MANAGER, employeeRepository);
        roleRepositoryMap.put(Role.ROLE_REPAIRER, employeeRepository);
    }

    public static Map<Role, EntityInfoRepository> getRoleRepositoryMap() {
        return roleRepositoryMap;
    }

    public static UserDetailsImpl getUserDetails(Authentication auth) {
        return (UserDetailsImpl)auth.getPrincipal();
    }
}
