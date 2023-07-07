package com.atorres.nttdata.client.infraestructure.repository;

import com.atorres.nttdata.client.infraestructure.repository.dto.ClientDao;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ClientRepository extends ReactiveMongoRepository<ClientDao,String> {
}
