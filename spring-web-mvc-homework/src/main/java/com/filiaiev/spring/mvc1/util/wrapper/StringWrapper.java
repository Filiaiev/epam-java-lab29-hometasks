package com.filiaiev.spring.mvc1.util.wrapper;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StringWrapper {

    @NotNull
    String string;
}
