package com.atorres.nttdata.client.infraestructure.rest;

import com.atorres.nttdata.client.application.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientResource {
    private final ClientService clientService;


}
