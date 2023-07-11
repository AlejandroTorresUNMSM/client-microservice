package com.atorres.nttdata.client.mapper;

import com.atorres.nttdata.client.model.ClientPost;
import com.atorres.nttdata.client.model.dao.ClientDao;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public ClientDao clientposttoClientDao(ClientPost clientPost){
        ClientDao clientDao = new ClientDao();
        clientDao.setId(generateId());
        clientDao.setName(clientPost.getName());
        clientDao.setTypeDocument(clientPost.getTypeDocument());
        clientDao.setNroDocument(clientPost.getNroDocument());
        clientDao.setTypeClient(clientPost.getTypeClient());
        return clientDao;
    }

    public ClientDao clientposttoClientDao(final ClientPost clientPost, final String id){
        ClientDao clientDao = new ClientDao();
        clientDao.setId(id);
        clientDao.setName(clientPost.getName());
        clientDao.setTypeDocument(clientPost.getTypeDocument());
        clientDao.setNroDocument(clientPost.getNroDocument());
        clientDao.setTypeClient(clientPost.getTypeClient());
        return clientDao;
    }

    private String generateId(){
        return java.util.UUID.randomUUID().toString().replaceAll("-","");
    }
}
