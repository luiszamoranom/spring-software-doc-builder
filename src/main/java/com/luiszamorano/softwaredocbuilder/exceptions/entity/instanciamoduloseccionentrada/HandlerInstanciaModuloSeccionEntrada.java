package com.luiszamorano.softwaredocbuilder.exceptions.entity.instanciamoduloseccionentrada;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerInstanciaModuloSeccionEntrada {
    @ExceptionHandler(SinInstanciaModuloSeccionEntradaException.class)
    public ResponseEntity<Void> handleSinInstanciaModuloSeccionEntradaException() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(InstanciaModuloSeccionEntradaNoEncontradaException.class)
    public ResponseEntity<String> handleInstanciaModuloSeccionEntradaNoEncontradaException(InstanciaModuloSeccionEntradaNoEncontradaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InstanciaModuloSeccionEntradaYaExisteException.class)
    public ResponseEntity<String> handleInstanciaModuloSeccionEntradaYaExisteException(InstanciaModuloSeccionEntradaYaExisteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}