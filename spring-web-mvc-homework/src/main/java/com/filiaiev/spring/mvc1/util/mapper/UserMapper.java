package com.filiaiev.spring.mvc1.util.mapper;

import com.filiaiev.spring.mvc1.dto.user.UserProfileDto;
import com.filiaiev.spring.mvc1.dto.user.UserRegisterDto;
import com.filiaiev.spring.mvc1.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUserFromUserRegister(UserRegisterDto dto);

    UserRegisterDto toUserRegister(User user);

    UserProfileDto toUserProfile(User user);
}
