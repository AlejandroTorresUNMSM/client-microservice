package com.atorres.nttdata.client.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {
    /**.
     * HttpStatus de la excepcion
     */
    private final HttpStatus status;

    /**.
     * Constructor CunstomException
     * @param httpStatus httpstatus
     * @param message mensaje de la excepcion
     */
    public CustomException(final HttpStatus httpStatus, final String message) {
        super(message);
        this.status = httpStatus;
    }

    /**.
     * Metodo que devuelve el status de la excepcion
     * @return httpstatus
     */
    public HttpStatus getStatus() {
        return status;
    }
}
