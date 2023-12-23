package com.luiszamorano.softwaredocbuilder.exceptions.entity.entrada;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerEntrada {
    @ExceptionHandler(SinEntradaException.class)
    public ResponseEntity<Void> handleSinEntradaException() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(EntradaNoEncontradaException.class)
    public ResponseEntity<String> handleEntradaNoEncontradaException(EntradaNoEncontradaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(EntradaYaExisteException.class)
    public ResponseEntity<String> handleUsuarioYaExisteException(EntradaYaExisteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
