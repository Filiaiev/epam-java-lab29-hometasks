package com.filiaiev.spring.mvc1.api;

import com.filiaiev.spring.mvc1.dto.client.ClientDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Api(tags = "Client Api")
@RequestMapping("/api/v1/clients")
public interface ClientApi {

    @ApiOperation("Get client by id")
    @GetMapping("/{client_id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_MANAGER or ROLE_CLIENT')")
    ClientDto getClient(@PathVariable("client_id") Long id);

    @ApiOperation("Add cash to client`s account")
    @PatchMapping("/{client_id}/cash")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    ClientDto addClientCash(@PathVariable("client_id") Long id,
                            @RequestBody BigDecimal cash);
}
