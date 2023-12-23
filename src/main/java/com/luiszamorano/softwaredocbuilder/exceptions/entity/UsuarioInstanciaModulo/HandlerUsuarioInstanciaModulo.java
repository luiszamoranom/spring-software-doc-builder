package com.luiszamorano.softwaredocbuilder.exceptions.entity.UsuarioInstanciaModulo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerUsuarioInstanciaModulo {
    @ExceptionHandler(SinUsuarioInstanciaModuloException.class)
    public ResponseEntity<Void> handleSinUsuarioInstanciaModuloException() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(UsuarioInstanciaModuloNoEncontradoException.class)
    public ResponseEntity<String> handleUsuarioInstanciaModuloNoEncontradoException(UsuarioInstanciaModuloNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(UsuarioInstanciaModuloYaExisteException.class)
    public ResponseEntity<String> handleUsuarioInstanciaModuloYaExisteException(UsuarioInstanciaModuloYaExisteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}