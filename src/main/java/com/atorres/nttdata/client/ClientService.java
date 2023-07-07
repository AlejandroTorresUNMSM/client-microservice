package com.atorres.nttdata.client;

import com.atorres.nttdata.client.model.dao.ClientDao;
import com.atorres.nttdata.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class ClientService {
    @Autowired
    private ClientRepository dao;

    public Flux<ClientDao> findAll() {
        return dao.findAll();
    }

    public Mono<ClientDao> findById(String id) {
        return dao.findById(id);
    }

    public Mono<ClientDao> findByDocument(String nroDocument) {
        return dao.findByNroDocument(nroDocument);
    }

    public Mono<ClientDao> save(ClientDao clientDao) {
        return dao.save(clientDao);
    }

    public Mono<Void> delete(ClientDao clientDao) {
        return dao.delete(clientDao);
    }
}
