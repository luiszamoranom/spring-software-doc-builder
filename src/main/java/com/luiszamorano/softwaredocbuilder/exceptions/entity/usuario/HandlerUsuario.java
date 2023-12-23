package com.luiszamorano.softwaredocbuilder.exceptions.entity.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerUsuario {
    @ExceptionHandler(SinUsuarioException.class)
    public ResponseEntity<Void> handleSinUsuarioException() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<String> handleUsuarioNoEncontradoException(UsuarioNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(UsuarioYaExisteException.class)
    public ResponseEntity<String> handleUsuarioYaExisteException(UsuarioYaExisteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(CredencialesInvalidasUsuarioException.class)
    public ResponseEntity<String> handleCredencialesInvalidasUsuarioException(CredencialesInvalidasUsuarioException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
}
