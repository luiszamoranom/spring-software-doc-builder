package com.luiszamorano.softwaredocbuilder.exceptions.entity.instanciamoduloproyectoseccion;

import com.luiszamorano.softwaredocbuilder.exceptions.entity.instanciamoduloproyecto.InstanciaModuloProyectoYaExisteException;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerInstanciaModuloProyectoSeccion {
    @ExceptionHandler(SinInstanciaModuloProyectoSeccionException.class)
    public ResponseEntity<Void> handleSinInstanciaModuloProyectoSeccionException() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ExceptionHandler(InstanciaModuloProyectoSeccionNoEncontradaException.class)
    public ResponseEntity<String> handleInstanciaModuloProyectoSeccionNoEncontradaException(InstanciaModuloProyectoSeccionNoEncontradaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InstanciaModuloProyectoYaExisteException.class)
    public ResponseEntity<String> handleInstanciaModuloProyectoYaExisteException(InstanciaModuloProyectoYaExisteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
