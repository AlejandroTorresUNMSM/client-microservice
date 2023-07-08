package com.atorres.nttdata.client.controller;

import com.atorres.nttdata.client.service.ClientService;
import com.atorres.nttdata.client.mapper.ClientMapper;
import com.atorres.nttdata.client.model.dao.ClientDao;
import com.atorres.nttdata.client.model.ClientPost;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.net.URI;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/client")
@Slf4j
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public Flux<ClientDao> getListClients(){
        return clientService.findAll();
    }
    public Mono<ResponseEntity<Flux<ClientDao>>> lista(){
        return Mono.just(
                ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(clientService.findAll())
        );
    }

    @PostMapping
    public Mono<ResponseEntity<ClientDao>> createClient(@Valid @RequestBody Mono<ClientPost> clientMono) {
        return clientMono.flatMap(client -> {
                    return clientService.save(client).map(p -> {
                        log.info("Cliente creado con éxito");
                        return ResponseEntity
                                .created(URI.create("/api/client/".concat(p.getId())))
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(p);
                    });
                });
    }


    @GetMapping("/{id}")
    public Mono<ResponseEntity<ClientDao>> getClient(@PathVariable String id){
        return clientService.findById(id).map(p -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p));
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<ClientDao>> updateClient(@PathVariable String id, @RequestBody ClientPost clientPost){
        return clientService.update(id,clientPost).map(p->ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(p));
    }


    @DeleteMapping("/{id}")
    public Mono<Void> deleteClient(@PathVariable String id){
        return clientService.delete(id);
    }
}
