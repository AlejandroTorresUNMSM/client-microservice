package com.atorres.nttdata.client.repository;

import com.atorres.nttdata.client.model.dao.ClientDao;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ClientRepository extends ReactiveMongoRepository
        <ClientDao, String> {
    /**.
     * Repositorio para REST con la base mongo client
     * @param nroDocument nro del documento
     * @return retorna un clientDao
     */
    Mono<ClientDao> findByNroDocument(String nroDocument);
}
