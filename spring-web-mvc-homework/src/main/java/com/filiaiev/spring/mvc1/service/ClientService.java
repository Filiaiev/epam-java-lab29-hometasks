package com.filiaiev.spring.mvc1.service;

import com.filiaiev.spring.mvc1.dto.client.ClientDto;

import java.math.BigDecimal;

public interface ClientService {

    ClientDto getClient(Long id);

    ClientDto addClientCash(Long id, BigDecimal cash);
}
