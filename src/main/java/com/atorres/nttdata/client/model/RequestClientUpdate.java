package com.atorres.nttdata.client.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestClientUpdate {
  /**.
   * Tipo del documento
   */
  @NotBlank
  private String typeDocument;
  /**.
   * Numero del documento
   */
  @NotBlank
  private String nroDocument;
  /**.
   * Nombre del cliente
   */
  @NotBlank
  private String name;
}
