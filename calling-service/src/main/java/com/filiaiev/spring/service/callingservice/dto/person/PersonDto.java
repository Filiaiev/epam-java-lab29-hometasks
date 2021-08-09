package com.filiaiev.spring.service.callingservice.dto.person;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.filiaiev.spring.service.callingservice.dto.group.OnClientRegister;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonDto {

    @NotBlank(message = "{register.fail.empty.firstName}",
            groups = {OnClientRegister.class})
    private String firstName;

    @NotBlank(message = "{register.fail.empty.middleName",
            groups = {OnClientRegister.class})
    private String middleName;

    @NotBlank(message = "{register.fail.empty.lastName}",
            groups = {OnClientRegister.class})
    private String lastName;

    @NotNull(message = "{register.fail.empty.birthDate}",
            groups = {OnClientRegister.class})
    private LocalDate birthDate;
}
