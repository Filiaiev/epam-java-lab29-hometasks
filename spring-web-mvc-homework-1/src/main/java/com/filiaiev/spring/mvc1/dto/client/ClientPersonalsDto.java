package com.filiaiev.spring.mvc1.dto.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.filiaiev.spring.mvc1.dto.UserPersonalsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientPersonalsDto {

    private int id;

    private UserPersonalsDto user;
}
