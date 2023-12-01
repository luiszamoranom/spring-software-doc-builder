package com.luiszamorano.softwaredocbuilder.controller;

import com.luiszamorano.softwaredocbuilder.entity.Modulo;
import com.luiszamorano.softwaredocbuilder.response.GenericResponse;
import com.luiszamorano.softwaredocbuilder.service.ModuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/modulo")
public class ModuloController {
    @Autowired
    private ModuloService moduloService;

    @GetMapping(path = "/nombre/{nombre}")
    public ResponseEntity<GenericResponse<Optional<Modulo>>> findById(@PathVariable String nombre){
        Optional<Modulo> posibleModulo = moduloService.findById(nombre);
        if(posibleModulo.isPresent()){
            return new ResponseEntity<>(new GenericResponse<>(posibleModulo,"modulo encontrado con ese nombre"), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/")
    public ResponseEntity<GenericResponse<List<Modulo>>> findAll(){
        List<Modulo> modulos = moduloService.findAll();
        if(!modulos.isEmpty()){
            return new ResponseEntity<>(new GenericResponse<>(modulos,"modulos encontrados"),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/habilitados")
    public ResponseEntity<GenericResponse<List<Modulo>>> findByEstadoTrue(){
        List<Modulo> modulos = moduloService.findByEstadoTrue();
        if(!modulos.isEmpty()){
            return new ResponseEntity<>(new GenericResponse<>(modulos,"modulos encontrados y filtrados por estado true"),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/deshabilitados")
    public ResponseEntity<GenericResponse<List<Modulo>>>  findByEstadoFalse(){
        List<Modulo> modulos = moduloService.findByEstadoFalse();
        if(!modulos.isEmpty()){
            return new ResponseEntity<>(new GenericResponse<>(modulos,"modulos encontrados y filtrados por estado false"),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private record CambiarEstadoRecord(String nombre, Boolean estado){}
    @PatchMapping(path = "/cambiar_estado")
    public ResponseEntity<GenericResponse<Optional<Modulo>>> cambiarEstado(@RequestBody CambiarEstadoRecord record) {
        Optional<Modulo> posibleModulo = moduloService.cambiarEstado(record.nombre,record.estado);
        if(posibleModulo.isPresent()){
            return new ResponseEntity<>(new GenericResponse<>(posibleModulo,"modulo con estado actualizado"), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    private record UpdateRecord(String nombre, String descripcion){}
    @PatchMapping(path = "/actualizar")
    public ResponseEntity<GenericResponse<Optional<Modulo>>> update(@RequestBody UpdateRecord record) {
        Optional<Modulo> posibleModulo = moduloService.update(record.nombre,record.descripcion);
        if(posibleModulo.isPresent()){
            return new ResponseEntity<>(new GenericResponse<>(posibleModulo,"modulo  actualizado"), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
