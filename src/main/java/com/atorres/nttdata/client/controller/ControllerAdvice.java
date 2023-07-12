package com.atorres.nttdata.client.controller;

import com.atorres.nttdata.client.exception.CustomException;
import com.atorres.nttdata.client.exception.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    /**.
     * Metodo que captura una excepcion RuntimeException
     * @param ex excepcion
     * @return ErrorDto
     */
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorDto> runtimeExceptionHandler(
            final RuntimeException ex) {
        ErrorDto error = ErrorDto
                .builder()
                .httpStatus(HttpStatus.BAD_GATEWAY)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**.
     * Metodo que captura una excepcion CunstomExpception que fue creada
     * @param ex excepcion
     * @return ErrorDto
     */
    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ErrorDto> customExceptionHandler(
            final CustomException ex) {
        ErrorDto error = ErrorDto
                .builder()
                .httpStatus(ex.getStatus())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, ex.getStatus());
    }

}
