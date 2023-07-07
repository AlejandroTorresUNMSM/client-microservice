package com.atorres.nttdata.client.model.dao;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("client")
public class ClientDao {

    @Id
    private String id;
    private String typeDocument;
    private String nroDocument;
    private String name;

}