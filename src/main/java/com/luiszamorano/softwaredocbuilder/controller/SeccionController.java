package com.luiszamorano.softwaredocbuilder.controller;

import com.luiszamorano.softwaredocbuilder.entity.Seccion;
import com.luiszamorano.softwaredocbuilder.response.GenericResponse;
import com.luiszamorano.softwaredocbuilder.service.SeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/seccion")
public class SeccionController {
    @Autowired
    private SeccionService seccionService;

    @GetMapping("/")
    public ResponseEntity<GenericResponse<List<Seccion>>> findAll(){
        List<Seccion> secciones = seccionService.findAll();
        if(secciones.size() > 0){
            return new ResponseEntity<>(new GenericResponse<>(
                    secciones,"secciones encontradas"
            ), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findById")
    public ResponseEntity<GenericResponse<Seccion>> findById(@RequestParam String nombre){
        Optional<Seccion> posibleSeccion = seccionService.findById(nombre);
        if(posibleSeccion.isPresent()){
            return new ResponseEntity<>(new GenericResponse<>(
                    posibleSeccion.get(),"seccion con dicho nombre encontrada"
            ), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/save")
    public ResponseEntity<GenericResponse<Seccion>> save(@RequestParam String nombre){
        Optional<Seccion> posibleSeccion = seccionService.findById(nombre);
        if(posibleSeccion.isEmpty()){
            Seccion nuevaSeccion = new Seccion(nombre);
            seccionService.save(nuevaSeccion);
            return new ResponseEntity<>(new GenericResponse<>(
                    nuevaSeccion,"se crea la seccion con dicho nombre"
            ), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<GenericResponse<Seccion>> delete(@RequestParam String nombre){
        Optional<Seccion> posibleSeccion = seccionService.findById(nombre);
        if(posibleSeccion.isPresent()){
            seccionService.delete(posibleSeccion.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
