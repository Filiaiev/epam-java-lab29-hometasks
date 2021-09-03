package com.filiaiev.spring.mvc1.dto.user;

import com.filiaiev.spring.mvc1.dto.person.PersonDto;
import com.filiaiev.spring.mvc1.validation.annotation.UniqueUserContraint;
import com.filiaiev.spring.mvc1.validation.annotation.UserPasswordConstraint;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@UniqueUserContraint
@UserPasswordConstraint
public class UserRegisterDto {

    @NotBlank(message = "{register.fail.empty.email}")
    @Email(message = "{register.fail.pattern.email}")
    private String email;

    @NotBlank(message = "{register.fail.empty.login}")
    private String login;

    @NotBlank(message = "{register.fail.empty.password}")
    private String password;

    @NotBlank(message = "{register.fail.empty.repeatedPassword}")
    private String repeatedPassword;

    @NotNull(message = "{register.fail.empty.person}")
    @Valid
    private PersonDto person;
}
