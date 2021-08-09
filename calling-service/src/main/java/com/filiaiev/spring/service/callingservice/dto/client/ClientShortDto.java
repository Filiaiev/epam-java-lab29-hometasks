package com.filiaiev.spring.service.callingservice.dto.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.filiaiev.spring.service.callingservice.dto.user.UserShortDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientShortDto {

    private int id;
    private UserShortDto user;
}
