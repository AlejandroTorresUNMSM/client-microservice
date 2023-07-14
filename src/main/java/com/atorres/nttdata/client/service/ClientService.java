package com.atorres.nttdata.client.service;

import com.atorres.nttdata.client.exception.CustomException;
import com.atorres.nttdata.client.mapper.ClientMapper;
import com.atorres.nttdata.client.model.ClientPost;
import com.atorres.nttdata.client.model.RequestClientUpdate;
import com.atorres.nttdata.client.model.dao.ClientDao;
import com.atorres.nttdata.client.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@Service
public class ClientService {
    /**.
     * Repositorio para cliente
     */
    @Autowired
    private ClientRepository dao;
    /**.
     * Mapper Cliente
     */
    @Autowired
    private ClientMapper clientMapper;

    /**.
     * Metodo que encuentra todos los clientes
     * @return lista de clientes
     */
    public Flux<ClientDao> findAll() {
        return dao.findAll();
    }

    /**.
     * Metodo que trae un cliente por su Id
     * @param id id del cliente
     * @return clientDao
     */
    public  Mono<ClientDao> findById(final String id) {
        //obtengo el cliente por id si no lo encuentra devuelve una excepcion
        return dao.findById(id)
                .switchIfEmpty(Mono.defer(() -> {
                    log.error("No se encontro al cliente {}", id);
                    return Mono.error(new CustomException(HttpStatus.NOT_FOUND,
                            "No se encontró al cliente"));
                }));
    }

    /**.
     * Metodo para crear un cliente
     * @param client cliente request
     * @return clienteDao
     */
    public Mono<ClientDao> save(final ClientPost client) {
        return dao.findAll()
                .filter(cl -> !Objects
                        .equals(cl.getTypeDocument(), client.getTypeDocument())
                        || !Objects
                        .equals(cl.getNroDocument(), client.getNroDocument()))
                .any(cl -> true)
                .flatMap(exist -> !exist
                        ? Mono.error(new CustomException(HttpStatus.BAD_REQUEST,
                                "Ya existe el nroDocumento y tipo"))
                        : dao.save(clientMapper.clientposttoClientDao(client)));
    }

    /**.
     * Metodo para borrar un cliente
     * @param id id del cliente
     * @return Vacio
     */
    public Mono<Void> delete(final String id) {
        //reviso que el cliente exista
        Mono<Boolean> existeClient = dao.findById(id).hasElement();
        //reviso el flag del client si no existe lanzo un mensaje de error
        return existeClient.flatMap(exists -> exists ? dao.deleteById(id)
                : Mono.error(new CustomException(HttpStatus.NOT_FOUND,
                "No existe el cliente a eliminar")));
    }

    /**.
     * Metodo para actualizar cliente
     * @param id id del cliente
     * @param request request del cliente
     * @return ClienteDao
     */
    public Mono<ClientDao> update(
            final String id,
            final RequestClientUpdate request) {
        return dao.findById(id)
                .switchIfEmpty(
                        Mono.error(new CustomException(HttpStatus.NOT_FOUND,
                                "No existe el cliente")))
                .map(client -> clientMapper.
                        clientposttoClientDaoUpdate(client, request))
                .flatMap(client -> dao.save(client));
    }
}
