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
        //obtengo el cliente por id si no lo encuentra devuelve una excepcion
        return dao.findById(id)
                .switchIfEmpty(Mono.error(new CustomException(HttpStatus.NOT_FOUND,"No se encontro al cliente")));
    }

    public Mono<ClientDao> save(ClientPost client) {
        //reviso si existe un cliente con un nroDocumento
        Mono<Boolean> existeClient = dao.findByNroDocument(client.getNroDocument()).hasElement();
        //si existe un cliente con el nroDocumento lanzo una excepcion
        return existeClient.flatMap(exist -> exist ? Mono.error(new CustomException(HttpStatus.BAD_REQUEST,"El numero de documento ya esta en uso"))
                : dao.save(clientMapper.clientposttoClientDao(client)));
    }

    public Mono<Void> delete(String id) {
        //reviso que el cliente exista
        Mono<Boolean> existeClient = dao.findById(id).hasElement();
        //reviso el flag del client si no existe lanzo un mensaje de error
        return existeClient.flatMap(exists -> exists ? dao.deleteById(id) : Mono.error(new CustomException(HttpStatus.NOT_FOUND, "No existe el cliente a eliminar")));
    }

    public Mono<ClientDao> update(String id ,ClientPost clientPost){
        //reviso que el cliente exista y ademas que coincida con el nroDocumento y el typeDocument
        //ahorita lo unico que se podria modificar seria el name y el type
        Mono<Boolean> existeClient = dao.findById(id)
                .filter(cl -> cl.getTypeDocument().equals(clientPost.getTypeDocument()))
                .filter(cl -> cl.getNroDocument().equals(clientPost.getNroDocument()))
                .hasElement();
        //reviso el flag si existe el cliente  sino lanzo un mensaje de error
        return existeClient
                .flatMap(exists -> exists ?
                dao.save(clientMapper.clientposttoClientDao(clientPost,id)) :
                Mono.error(new CustomException(HttpStatus.NOT_FOUND, "No existe el cliente o el nrodocumento no es el correcto")));
    }
}
