package com.luiszamorano.softwaredocbuilder.exceptions.entity.contenido;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerContenido {
    @ExceptionHandler(SinContenidoException.class)
    public ResponseEntity<Void> handleSinContenidoException() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(ContenidoNoEncontradoException.class)
    public ResponseEntity<String> handleContenidoNoEncontradoException(ContenidoNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ContenidoYaExisteException.class)
    public ResponseEntity<String> handleContenidoYaExisteException(ContenidoYaExisteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
