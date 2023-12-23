package com.luiszamorano.softwaredocbuilder.exceptions.entity.instanciamoduloproyecto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerInstanciaModuloProyecto {
    @ExceptionHandler(SinInstanciaModuloProyectoException.class)
    public ResponseEntity<Void> handleSinInstanciaModuloProyectoException() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(InstanciaModuloProyectoNoEncontradaException.class)
    public ResponseEntity<String> handleInstanciaModuloProyectoNoEncontradaException(InstanciaModuloProyectoNoEncontradaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InstanciaModuloProyectoYaExisteException.class)
    public ResponseEntity<String> handleInstanciaModuloProyectoYaExisteException(InstanciaModuloProyectoYaExisteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}