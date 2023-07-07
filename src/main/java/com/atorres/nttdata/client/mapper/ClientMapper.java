package com.atorres.nttdata.client.mapper;

import com.atorres.nttdata.client.model.Client;
import com.atorres.nttdata.client.model.ClientRequest;
import com.atorres.nttdata.client.model.dao.ClientDao;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public ClientDao clientRequesttoClientDao(ClientRequest clientRequest){
        ClientDao clientDao = new ClientDao();
        clientDao.setId(generateId());
        clientDao.setName(clientRequest.getName());
        clientDao.setTypeDocument(clientRequest.getTypeDocument());
        clientDao.setNroDocument(clientRequest.getNroDocument());
        return clientDao;
    }

    private String generateId(){
        return java.util.UUID.randomUUID().toString().replaceAll("-","");
    }
}
