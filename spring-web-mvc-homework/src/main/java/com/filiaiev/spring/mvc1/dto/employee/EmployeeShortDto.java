package com.filiaiev.spring.mvc1.dto.employee;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.filiaiev.spring.mvc1.dto.group.OnOrderManagerUpdate;
import com.filiaiev.spring.mvc1.dto.user.UserShortDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Null;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeShortDto {

    private int id;

    @Null(message = "{update.fail.manager.user}",
            groups = {OnOrderManagerUpdate.class})
    @Valid
    private UserShortDto user;
}
