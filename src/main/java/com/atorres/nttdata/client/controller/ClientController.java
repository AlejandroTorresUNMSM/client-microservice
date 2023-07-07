package com.atorres.nttdata.client.controller;

import com.atorres.nttdata.client.ClientService;
import com.atorres.nttdata.client.mapper.ClientMapper;
import com.atorres.nttdata.client.model.dao.ClientDao;
import com.atorres.nttdata.client.model.ClientRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientMapper clientMapper;

    @GetMapping
    public Flux<ClientDao> getList(){
        ClientRequest clientRequest = new ClientRequest();
        return clientService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ClientDao> create(@Valid @RequestBody Mono<ClientRequest> clientRequest){
        return clientRequest.flatMap(clientr -> clientService.save(clientMapper.clientRequesttoClientDao(clientr)));
    }

    @GetMapping("/{id}")
    public Mono<ClientDao> getId(@PathVariable String id){
        return clientService.findById(id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return clientService.findById(id).flatMap(p ->clientService.delete(p));
    }
}
