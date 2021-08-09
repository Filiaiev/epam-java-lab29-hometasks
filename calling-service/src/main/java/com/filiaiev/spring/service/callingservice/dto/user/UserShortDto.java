package com.filiaiev.spring.service.callingservice.dto.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.filiaiev.spring.service.callingservice.dto.group.OnOrderManagerUpdate;
import com.filiaiev.spring.service.callingservice.dto.person.PersonShortDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Null;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserShortDto {

    @Null(message = "{update.user.fail.access.personals}",
        groups = {OnOrderManagerUpdate.class})
    private String email;

    @Null(message = "{update.user.fail.access.personals}")
    @Valid
    private PersonShortDto person;
}
