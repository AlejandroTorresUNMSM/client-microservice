package com.atorres.nttdata.client.service;

import com.atorres.nttdata.client.exception.CustomException;
import com.atorres.nttdata.client.mapper.ClientMapper;
import com.atorres.nttdata.client.model.ClientPost;
import com.atorres.nttdata.client.model.dao.ClientDao;
import com.atorres.nttdata.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

        return existeClient.flatMap(exist -> exist ? Mono.error(new CustomException(HttpStatus.BAD_REQUEST,"El cliente Existe"))
                : dao.save(clientMapper.clientRequesttoClientDao(client)));
    }

    public Mono<Void> delete(ClientDao clientDao) {
        return dao.delete(clientDao);
    }
}
