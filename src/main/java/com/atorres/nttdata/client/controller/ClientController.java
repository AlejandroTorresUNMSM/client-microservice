package com.atorres.nttdata.client.controller;

import com.atorres.nttdata.client.service.ClientService;
import com.atorres.nttdata.client.model.dao.ClientDao;
import com.atorres.nttdata.client.model.ClientPost;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/client")
@Slf4j
public class ClientController {
  /**
   * .
   * ClienteService
   */
  @Autowired
  private ClientService clientService;

  /**
   * .
   * Metodo que retorna todos los clientes
   *
   * @return lista clientDao
   */
  @GetMapping
  public Flux<ClientDao> getListClients() {
    return clientService.findAll();
  }

  /**
   * .
   * Metodo para crear un cliente
   *
   * @param cp cliente request
   * @return cliente
   */
  @PostMapping
  public Mono<ClientDao> createClient(
          @Valid @RequestBody
          final Mono<ClientPost> cp) {
    return cp.flatMap(client -> clientService.save(client));
  }

  /**
   * .
   * Metodo para encontrar un cliente por su id
   *
   * @param id id del cliente
   * @return cliente
   */
  @GetMapping("/{id}")
  public Mono<ClientDao> getClient(
          @PathVariable final String id) {
    return clientService.findById(id);
  }

  /**
   * .
   * Metodo para actualizar un cliente
   *
   * @param id id cliente
   * @param cp cliente request
   * @return cliente
   */
  @PutMapping("/update/{id}")
  public Mono<ClientDao> updateClient(
          @PathVariable final String id,
          @RequestBody final ClientPost cp) {
    return clientService.update(id, cp);
  }

  /**
   * .
   * Metodo para eliminar un cliente
   *
   * @param id id cliente
   * @return void
   */
  @DeleteMapping("/{id}")
  public Mono<Void> deleteClient(
          @PathVariable final String id) {
    return clientService.delete(id);
  }
}
