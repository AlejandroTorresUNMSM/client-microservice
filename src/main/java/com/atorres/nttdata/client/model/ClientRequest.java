package com.atorres.nttdata.client.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClientRequest {
    @NotBlank
    private String typeDocument;
    @NotBlank
    private String nroDocument;
    @NotBlank
    private String name;

}
