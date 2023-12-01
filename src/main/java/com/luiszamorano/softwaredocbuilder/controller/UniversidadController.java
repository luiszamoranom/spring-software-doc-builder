package com.luiszamorano.softwaredocbuilder.controller;

import com.luiszamorano.softwaredocbuilder.dto.GenericDTO;
import com.luiszamorano.softwaredocbuilder.entity.Universidad;
import com.luiszamorano.softwaredocbuilder.response.GenericResponse;
import com.luiszamorano.softwaredocbuilder.service.UniversidadService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/universidad")
public class UniversidadController {
    @Autowired
    private UniversidadService universidadService;

    @GetMapping(path = "/abreviacion/{abreviacion}")
    public ResponseEntity<GenericResponse<Optional<Universidad>>> findById(@PathVariable String abreviacion){
        Optional<Universidad> posibleUniversidad = universidadService.findById(abreviacion);
        if(posibleUniversidad.isPresent()){
            return new ResponseEntity(new GenericResponse<>(posibleUniversidad,"universidad encontrada con esa abreviacion"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new GenericResponse<>(Optional.empty(),"no existe universidad con esa abreviacion"),HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/")
    public ResponseEntity<GenericResponse<List<Universidad>>> findAll(){
        List<Universidad> universidades = universidadService.findAll();
        if(!universidades.isEmpty()){
            return new ResponseEntity<>(new GenericResponse<>(universidades,"universidades encontradas"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new GenericResponse<>(Collections.emptyList(),"sin universidades"),HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/habilitadas")
    public ResponseEntity<GenericResponse<List<Universidad>>> findByEstadoTrue(){
        List<Universidad> universidades = universidadService.findByEstadoTrue();
        if(!universidades.isEmpty()){
            return new ResponseEntity<>(new GenericResponse<>(universidades,"universidades encontradas y filtradas por estado habilitado"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new GenericResponse<>(Collections.emptyList(),"sin universidades habilitadas"),HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/deshabilitadas")
    public ResponseEntity<GenericResponse<List<Universidad>>> findByEstadoFalse(){
        List<Universidad> universidades = universidadService.findByEstadoFalse();
        if(!universidades.isEmpty()){
            return new ResponseEntity<>(new GenericResponse<>(universidades,"universidades encontradas y filtradas por estado deshabilitado"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new GenericResponse<>(Collections.emptyList(),"sin universidades deshabilitadas"),HttpStatus.NO_CONTENT);
    }


    private record CambiarEstadoRecord(String abreviacion, Boolean estado){}
    @PatchMapping(path = "/cambiar_estado")
    public ResponseEntity<GenericResponse<Optional<Universidad>>> cambiarEstado(@RequestBody CambiarEstadoRecord record) {
        Optional<Universidad> posibleUniversidad = universidadService.cambiarEstado(record.abreviacion,record.estado);
        if(posibleUniversidad.isPresent()){
            return new ResponseEntity(new GenericResponse<>(posibleUniversidad,"universidad actualizada con dicho estado"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new GenericResponse<>(Optional.empty(),"no existe universidad con esa abreviacion"),HttpStatus.NO_CONTENT);
    }

    private record UpdateRecord(String abreviacion, String nombre){}
    @PatchMapping("/actualizar")
    public ResponseEntity<GenericResponse<Optional<Universidad>>> update(@RequestBody UpdateRecord record ) {
        Optional<Universidad> posibleUniversidad = universidadService.update(record.abreviacion,record.nombre);
        if(posibleUniversidad.isPresent()){
            return new ResponseEntity(new GenericResponse<>(posibleUniversidad,"universidad actualizada"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new GenericResponse<>(Optional.empty(),"no existe universidad con esa abreviacion"),HttpStatus.NO_CONTENT);
    }
}
