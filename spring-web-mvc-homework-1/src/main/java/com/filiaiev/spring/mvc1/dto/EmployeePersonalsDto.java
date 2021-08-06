package com.filiaiev.spring.mvc1.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.filiaiev.spring.mvc1.dto.group.OnOrderManagerUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeePersonalsDto {

    private int id;

    @Null(message = "You don`t have permission to change employee personal info",
            groups = {OnOrderManagerUpdate.class})
    private UserPersonalsDto user;
}
