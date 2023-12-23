package com.luiszamorano.softwaredocbuilder.exceptions.entity.instanciamoduloseccion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerInstanciaModuloSeccion {
    @ExceptionHandler(SinInstanciaModuloSeccionException.class)
    public ResponseEntity<Void> handleSinInstanciaModuloSeccionException() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(InstanciaModuloSeccionNoEncontrada.class)
    public ResponseEntity<String> handleInstanciaModuloSeccionNoEncontrada(InstanciaModuloSeccionNoEncontrada ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InstanciaModuloSeccionYaExisteException.class)
    public ResponseEntity<String> handleInstanciaModuloSeccionYaExisteException(InstanciaModuloSeccionYaExisteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}