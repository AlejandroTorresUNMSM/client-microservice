package com.atorres.nttdata.client.repository;

import com.atorres.nttdata.client.model.Client;
import com.atorres.nttdata.client.model.dao.ClientDao;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ClientRepository extends ReactiveMongoRepository<ClientDao,String> {
    Mono<ClientDao> findByNroDocument(String nroDocument);
}
