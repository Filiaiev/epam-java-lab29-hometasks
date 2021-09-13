package com.filiaiev.spring.mvc1.util.wrapper;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CashWrapper {

    @NotNull
    private BigDecimal cash;
}
