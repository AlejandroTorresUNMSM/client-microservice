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
    public Flux<ClientDao> getList(){
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
    public Mono<ResponseEntity<ClientDao>> crear(@Valid @RequestBody Mono<ClientPost> clientMono) {
        return clientMono.flatMap(client -> {
                    return clientService.save(client).map(p -> {
                        log.info("Cliente creado con éxito");
                        return ResponseEntity
                                .created(URI.create("/api/client/".concat(p.getId())))
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(p);
                    });
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.CONFLICT, "El número de documento ya existe")))
                .onErrorResume(t -> {
                    log.error("Fallo en crear el cliente");
                    return Mono.error(t);
                });
    }


    @GetMapping("/{id}")
    public Mono<ResponseEntity<ClientDao>> ver(@PathVariable String id){
        return clientService.findById(id).map(p -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return clientService.findById(id).flatMap(p ->clientService.delete(p));
    }
}
