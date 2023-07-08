package com.atorres.nttdata.client.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClientPost {
    @NotBlank
    private String typeDocument;
    @NotBlank
    private String nroDocument;
    @NotBlank
    private String name;
    @NotBlank
    private String typeClient;

}
