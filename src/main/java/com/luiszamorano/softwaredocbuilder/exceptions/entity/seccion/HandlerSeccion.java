package com.luiszamorano.softwaredocbuilder.exceptions.entity.seccion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerSeccion {
    @ExceptionHandler(SinSeccionException.class)
    public ResponseEntity<Void> handleSinSeccionException() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(SeccionNoEncontradaException.class)
    public ResponseEntity<String> handleSeccionNoEncontradaException(SeccionNoEncontradaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(SeccionYaExisteException.class)
    public ResponseEntity<String> handleSeccionYaExisteException(SeccionYaExisteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}