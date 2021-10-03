package com.filiaiev.spring.mvc1.service;

import com.filiaiev.spring.mvc1.dto.user.UserProfileDto;

public interface UserService {

    UserProfileDto getUser(Long id);

}
