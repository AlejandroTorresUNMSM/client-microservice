package com.atorres.nttdata.client.application.service;

import com.atorres.nttdata.client.infraestructure.repository.dto.ClientDao;
import com.atorres.nttdata.client.infraestructure.rest.dto.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {
    Flux<ClientDao> findAll();
    Mono<ClientDao> findById();
    Mono<ClientDao> save(Client client);
    Mono<Void> delete(Client producto);
}
