package com.atorres.nttdata.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Client {
    /**.
     * Id del cliente
     */
    @JsonProperty("id")
    private String id;
    /**.
     * Tipos de documento
     */
    private String typeDocument;
    /**.
     * Numero del documento
     */
    private String nroDocumento;
    /**.
     * Nombre del cliente
     */
    private String name;
    /**.
     * Tipo del cliente
     */
    private String typeClient;
}
