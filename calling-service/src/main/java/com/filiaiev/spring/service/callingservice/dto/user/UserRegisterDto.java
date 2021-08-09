package com.filiaiev.spring.service.callingservice.dto.user;

import com.filiaiev.spring.service.callingservice.dto.group.OnClientRegister;
import com.filiaiev.spring.service.callingservice.dto.person.PersonDto;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserRegisterDto {

    @NotBlank(message = "{register.fail.empty.email}",
            groups = {OnClientRegister.class})
    @Email(message = "{register.fail.pattern.email}",
            groups = {OnClientRegister.class})
    private String email;

    @NotBlank(message = "{register.fail.empty.login}",
            groups = {OnClientRegister.class})
    private String login;

    @NotBlank(message = "{register.fail.empty.password}",
            groups = {OnClientRegister.class})
    private String password;

    @NotBlank(message = "{register.fail.empty.repeatedPassword}",
            groups = {OnClientRegister.class})
    private String repeatedPassword;

    @NotNull(message = "{register.fail.empty.person}",
            groups = {OnClientRegister.class})
    @Valid
    private PersonDto person;
}
