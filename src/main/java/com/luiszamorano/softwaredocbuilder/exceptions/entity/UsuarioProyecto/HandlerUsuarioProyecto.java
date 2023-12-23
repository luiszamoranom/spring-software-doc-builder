package com.luiszamorano.softwaredocbuilder.exceptions.entity.UsuarioProyecto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerUsuarioProyecto {
    @ExceptionHandler(SinUsuarioProyectoException.class)
    public ResponseEntity<Void> handleSinUsuarioProyectoException() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(UsuarioProyectoNoEncontradoException.class)
    public ResponseEntity<String> handleUsuarioProyectoNoEncontradoException(UsuarioProyectoNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(UsuarioProyectoYaExisteException.class)
    public ResponseEntity<String> handleUsuarioProyectoYaExisteException(UsuarioProyectoYaExisteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}