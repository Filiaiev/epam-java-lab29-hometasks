package com.filiaiev.spring.mvc1.util.mapper;

import com.filiaiev.spring.mvc1.dto.client.ClientDto;
import com.filiaiev.spring.mvc1.dto.client.ClientShortDto;
import com.filiaiev.spring.mvc1.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    Client toClient(ClientShortDto dto);

    Client toClient(ClientDto dto);

    ClientShortDto toClientShort(Client client);

    ClientDto toClientDto(Client client);
}
