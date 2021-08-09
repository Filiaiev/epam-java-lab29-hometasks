package com.filiaiev.spring.service.callingservice.dto.person;

import com.filiaiev.spring.service.callingservice.dto.group.OnOrderManagerUpdate;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;

@Data
@NoArgsConstructor
public class PersonShortDto {

    @Null(message = "{update.user.fail.access.personals}",
        groups = {OnOrderManagerUpdate.class})
    private String firstName;

    @Null(message = "{update.user.fail.access.personals}",
        groups = {OnOrderManagerUpdate.class})
    private String middleName;

    @Null(message = "{update.user.fail.access.personals}",
        groups = {OnOrderManagerUpdate.class})
    private String lastName;
}