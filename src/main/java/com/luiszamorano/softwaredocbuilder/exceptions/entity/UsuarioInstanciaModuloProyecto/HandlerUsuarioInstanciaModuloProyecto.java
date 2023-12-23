package com.luiszamorano.softwaredocbuilder.exceptions.entity.UsuarioInstanciaModuloProyecto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerUsuarioInstanciaModuloProyecto {
    @ExceptionHandler(SinUsuarioInstanciaModuloProyectoException.class)
    public ResponseEntity<Void> handleSinUsuarioInstanciaModuloProyectoException() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(UsuarioInstanciaModuloProyectoNoEncontradoException.class)
    public ResponseEntity<String> handleUsuarioInstanciaModuloProyectoNoEncontradoException(UsuarioInstanciaModuloProyectoNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(UsuarioInstanciaModuloProyectoYaExisteException.class)
    public ResponseEntity<String> handleUsuarioInstanciaModuloProyectoYaExisteException(UsuarioInstanciaModuloProyectoYaExisteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}