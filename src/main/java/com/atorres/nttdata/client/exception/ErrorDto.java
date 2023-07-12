package com.atorres.nttdata.client.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorDto {
    /**.
     * HttpStatus error
     */
    private HttpStatus httpStatus;
    /**.
     * Mensaje de error
     */
    private String message;
}
