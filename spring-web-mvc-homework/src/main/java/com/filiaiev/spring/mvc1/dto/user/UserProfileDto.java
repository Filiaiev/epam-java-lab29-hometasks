package com.filiaiev.spring.mvc1.dto.user;

import com.filiaiev.spring.mvc1.dto.person.PersonDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {

    private String email;

    private PersonDto person;
}
