package com.filiaiev.spring.mvc1.model;

import com.filiaiev.spring.mvc1.util.SecurityUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String login;
    private String password;
    private GrantedAuthority authority;
    private Role role;
    private Map<Role, Long> roleEntityIdMap;

    public UserDetailsImpl(User user) {
        id = user.getId();
        login = user.getLogin();
        password = user.getPassword();

        authority = new SimpleGrantedAuthority(user.getRole().name());
        role = user.getRole();

        Long roleEntityId = SecurityUtil.getRoleRepositoryMap().get(user.getRole()).getEntityIdByUser(user);
        roleEntityIdMap = new HashMap<>();
        roleEntityIdMap.put(user.getRole(), roleEntityId);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(authority);
    }

    public Long getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public Map<Role, Long> getRoleEntityIdMap() {
        return roleEntityIdMap;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
