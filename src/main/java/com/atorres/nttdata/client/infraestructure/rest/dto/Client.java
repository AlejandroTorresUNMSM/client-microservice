package com.atorres.nttdata.client.infraestructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Client {
    @JsonProperty("id")
    private String id;
    private String typeDocument;
    private String nroDocumento;
    private String name;
}
