package com.atorres.nttdata.client.mapper;

import com.atorres.nttdata.client.model.ClientPost;
import com.atorres.nttdata.client.model.RequestClientUpdate;
import com.atorres.nttdata.client.model.dao.ClientDao;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    /**.
     * Metodo que transforma Clientepost a ClienteDao
     * @param clientPost clientepost
     * @return clientdao
     */
    public ClientDao clientposttoClientDao(final ClientPost clientPost) {
        ClientDao clientDao = new ClientDao();
        clientDao.setId(generateId());
        clientDao.setName(clientPost.getName());
        clientDao.setTypeDocument(clientPost.getTypeDocument());
        clientDao.setNroDocument(clientPost.getNroDocument());
        clientDao.setTypeClient(clientPost.getTypeClient());
        return clientDao;
    }

    /**.
     * Metodo usado en el Update para actulizar el Client
     * @param clientDao clientpost
     * @param request idcliente
     * @return client
     */
    public ClientDao clientposttoClientDaoUpdate(
            final ClientDao clientDao,
            final RequestClientUpdate  request) {
        clientDao.setName(request.getName());
        clientDao.setTypeDocument(request.getTypeDocument());
        clientDao.setNroDocument(request.getNroDocument());
        return clientDao;
    }

    /**.
     * Metodo para generar un Id aleatorio
     * @return id aleatorio
     */
    private String generateId() {
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }
}
