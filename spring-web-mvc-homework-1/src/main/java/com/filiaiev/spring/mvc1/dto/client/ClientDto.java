package com.filiaiev.spring.mvc1.dto.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.filiaiev.spring.mvc1.dto.UserPersonalsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientDto {

    private int id;

    private UserPersonalsDto user;

    BigDecimal cash;
}
