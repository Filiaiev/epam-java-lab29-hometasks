package com.filiaiev.spring.mvc1.api;

import com.filiaiev.spring.mvc1.dto.user.UserProfileDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = "User api")
@RequestMapping("api/v1/users")
public interface UserApi {

    @GetMapping("/{user_id}")
    UserProfileDto getUser(@PathVariable("user_id") Long id);

}
