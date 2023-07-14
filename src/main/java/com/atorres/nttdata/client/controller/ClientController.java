package com.atorres.nttdata.client.controller;

import com.atorres.nttdata.client.model.RequestClientUpdate;
import com.atorres.nttdata.client.service.ClientService;
import com.atorres.nttdata.client.model.dao.ClientDao;
import com.atorres.nttdata.client.model.ClientPost;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
  @GetMapping(value = "/",
          produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<ClientDao> getListClients() {
    return clientService.findAll()
            .doOnNext(cl -> log.info("Cliente encontrado: {}", cl));
  }

  /**
   * .
   * Metodo para crear un cliente
   *
   * @param cp cliente request
   * @return cliente
   */
  @PostMapping(value = "/",
          produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Mono<ClientDao> createClient(
          @Valid @RequestBody
          final Mono<ClientPost> cp) {
    return cp.flatMap(client -> clientService.save(client))
            .doOnSuccess(v -> log.error("Cliente creado con exito"));
  }

  /**
   * .
   * Metodo para encontrar un cliente por su id
   *
   * @param id id del cliente
   * @return cliente
   */
  @GetMapping(value = "/{id}",
          produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Mono<ClientDao> getClient(
          @PathVariable final String id) {
    return clientService.findById(id)
            .doOnSuccess(v -> log.error("Cliente encontrado con exito"));
  }

  /**
   * .
   * Metodo para actualizar un cliente
   *
   * @param id id cliente
   * @param cp cliente request
   * @return cliente
   */
  @PutMapping(value = "/update/{id}",
          produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Mono<ClientDao> updateClient(
          @PathVariable final String id,
          @RequestBody final RequestClientUpdate cp) {
    return clientService.update(id, cp)
            .doOnSuccess(v -> log.error("Cliente actualizado con exito"));
  }

  /**
   * .
   * Metodo para eliminar un cliente
   *
   * @param id id cliente
   * @return void
   */
  @DeleteMapping(value = "/{id}",
          produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Mono<Void> deleteClient(
          @PathVariable final String id) {
    return clientService.delete(id)
            .doOnSuccess(v -> log.error("Cliente eliminado con exito"));
  }
}
