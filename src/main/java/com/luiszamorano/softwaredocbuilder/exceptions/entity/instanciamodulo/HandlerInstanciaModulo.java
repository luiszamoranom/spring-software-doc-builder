package com.luiszamorano.softwaredocbuilder.exceptions.entity.instanciamodulo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerInstanciaModulo {
    @ExceptionHandler(SinInstanciaModuloException.class)
    public ResponseEntity<Void> handleSinInstanciaModuloException() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(InstanciaModuloNoEncontradaException.class)
    public ResponseEntity<String> handleInstanciaModuloNoEncontradaException(InstanciaModuloNoEncontradaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InstanciaModuloYaExisteException.class)
    public ResponseEntity<String> handleInstanciaModuloYaExisteException(InstanciaModuloYaExisteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}