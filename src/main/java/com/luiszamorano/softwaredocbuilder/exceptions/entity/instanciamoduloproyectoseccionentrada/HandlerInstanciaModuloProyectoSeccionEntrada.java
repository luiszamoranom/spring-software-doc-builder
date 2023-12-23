package com.luiszamorano.softwaredocbuilder.exceptions.entity.instanciamoduloproyectoseccionentrada;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerInstanciaModuloProyectoSeccionEntrada {
    @ExceptionHandler(SinInstanciaModuloProyectoSeccionEntradaException.class)
    public ResponseEntity<Void> handleSinInstanciaModuloProyectoSeccionEntradaException() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(InstanciaModuloProyectoSeccionEntradaNoEncontradaException.class)
    public ResponseEntity<String> handleInstanciaModuloProyectoSeccionEntradaNoEncontradaException(InstanciaModuloProyectoSeccionEntradaNoEncontradaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InstanciaModuloProyectoSeccionEntradaYaExisteException.class)
    public ResponseEntity<String> handleInstanciaModuloProyectoSeccionEntradaYaExisteException(InstanciaModuloProyectoSeccionEntradaYaExisteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}