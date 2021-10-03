package com.filiaiev.spring.mvc1.service.impl;

import com.filiaiev.spring.mvc1.dto.client.ClientDto;
import com.filiaiev.spring.mvc1.exception.repository.RecordNotFoundException;
import com.filiaiev.spring.mvc1.model.Client;
import com.filiaiev.spring.mvc1.model.Role;
import com.filiaiev.spring.mvc1.model.UserDetailsImpl;
import com.filiaiev.spring.mvc1.repository.ClientRepository;
import com.filiaiev.spring.mvc1.service.ClientService;
import com.filiaiev.spring.mvc1.util.SecurityUtil;
import com.filiaiev.spring.mvc1.util.mapper.ClientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public ClientDto getClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(RecordNotFoundException::new);

        ClientDto clientDto = ClientMapper.INSTANCE.toClientDto(client);
        UserDetailsImpl userDetails =
                SecurityUtil.getUserDetails(SecurityContextHolder.getContext().getAuthentication());

        if(userDetails.getRole() == Role.ROLE_MANAGER)
            return clientDto;

        if(!userDetails.getRoleEntityIdMap().get(userDetails.getRole()).equals(id))
            throw new RecordNotFoundException();

        return clientDto;
    }

    @Override
    public ClientDto addClientCash(Long id, BigDecimal cash) {
        Client client = clientRepository.findById(id).orElseThrow(RecordNotFoundException::new);
        BigDecimal currentTotalCash = client.getCash().add(cash);
        log.info("Client`s #{} cash changed from {} to {}", client.getId(), client.getCash(), currentTotalCash);
        client.setCash(currentTotalCash);
        return ClientMapper.INSTANCE.toClientDto(client);
    }
}
