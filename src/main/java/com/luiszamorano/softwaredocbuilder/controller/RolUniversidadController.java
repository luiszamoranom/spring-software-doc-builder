package com.luiszamorano.softwaredocbuilder.controller;

import com.luiszamorano.softwaredocbuilder.entity.RolUniversidad;
import com.luiszamorano.softwaredocbuilder.response.GenericResponse;
import com.luiszamorano.softwaredocbuilder.service.RolUniversidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/roluniversidad")
public class RolUniversidadController {
    @Autowired
    private RolUniversidadService rolUniversidadService;

    private record FindByIdRecord(Long id){}
    @GetMapping(path = "/id/{id}")
    public ResponseEntity<GenericResponse<Optional<RolUniversidad>>> findById(@ModelAttribute FindByIdRecord record){
        Optional<RolUniversidad> posibleRolPlataforma = rolUniversidadService.findById(record.id);
        if(posibleRolPlataforma.isPresent()){
            return new ResponseEntity<>(new GenericResponse<>(posibleRolPlataforma,"rol encontrado con ese id"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new GenericResponse<>(Optional.empty(),"no existe rol con ese id"), HttpStatus.NO_CONTENT);
    }

    private record FindByNombreRecord(String nombre){}
    @GetMapping(path = "/nombre/{nombre}")
    public ResponseEntity<GenericResponse<Optional<RolUniversidad>>> findByNombre(@ModelAttribute FindByNombreRecord record){
        Optional<RolUniversidad> posibleRolPlataforma = rolUniversidadService.findByNombre(record.nombre);
        if(posibleRolPlataforma.isPresent()){
            return new ResponseEntity<>(new GenericResponse<>(posibleRolPlataforma,"rol encontrado con ese nombre"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new GenericResponse<>(Optional.empty(),"no existe rol con ese nombre"), HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/")
    public ResponseEntity<GenericResponse<List<RolUniversidad>>> findAll(){
        List<RolUniversidad> roles = rolUniversidadService.findAll();
        if(!roles.isEmpty()){
            return new ResponseEntity<>(new GenericResponse<>(roles,"roles encontrados"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new GenericResponse<>(Collections.emptyList(),"sin roles"),HttpStatus.NO_CONTENT);
    }
}
