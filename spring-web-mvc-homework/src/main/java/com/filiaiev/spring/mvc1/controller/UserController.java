package com.filiaiev.spring.mvc1.controller;

import com.filiaiev.spring.mvc1.api.UserApi;
import com.filiaiev.spring.mvc1.dto.user.UserProfileDto;
import com.filiaiev.spring.mvc1.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    public UserProfileDto getUser(Long id) {
        return userService.getUser(id);
    }

}
