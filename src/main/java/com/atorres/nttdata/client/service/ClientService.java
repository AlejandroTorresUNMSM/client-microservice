package com.atorres.nttdata.client.service;

import com.atorres.nttdata.client.mapper.ClientMapper;
import com.atorres.nttdata.client.model.ClientPost;
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
    @Autowired
    private ClientMapper clientMapper;

    public Flux<ClientDao> findAll() {
        return dao.findAll();
    }

    public Mono<ClientDao> findById(String id) {
        return dao.findById(id);
    }

    public Mono<ClientDao> save(ClientPost client) {
        Mono<Boolean> existeClient = dao.findByNroDocument(client.getNroDocument()).hasElement();

        //return dao.findByNroDocument(client.getNroDocument()).switchIfEmpty(dao.insert(clientMapper.clientRequesttoClientDao(client)));
        return existeClient.flatMap(exist -> exist ? Mono.error(new Exception("El client existe"))
                : dao.save(clientMapper.clientRequesttoClientDao(client)));
    }

    public Mono<Void> delete(ClientDao clientDao) {
        return dao.delete(clientDao);
    }
}
