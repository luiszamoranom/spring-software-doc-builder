package com.luiszamorano.softwaredocbuilder.exceptions.entity.contenidoentrada;

import com.luiszamorano.softwaredocbuilder.exceptions.entity.contenido.SinContenidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerContenidoEntrada {
    @ExceptionHandler(SinContenidoException.class)
    public ResponseEntity<Void> handleSinContenidoException() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(ContenidoEntradaNoEncontradoException.class)
    public ResponseEntity<String> handleContenidoEntradaNoEncontradoException(ContenidoEntradaNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ContenidoEntradaYaExisteException.class)
    public ResponseEntity<String> handleContenidoEntradaYaExisteException(ContenidoEntradaYaExisteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
