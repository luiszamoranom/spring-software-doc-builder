package com.luiszamorano.softwaredocbuilder.controller;

import com.luiszamorano.softwaredocbuilder.entity.RolPlataforma;
import com.luiszamorano.softwaredocbuilder.response.GenericResponse;
import com.luiszamorano.softwaredocbuilder.service.RolPlataformaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/rolplataforma")
public class RolPlataformaController {
    @Autowired
    private RolPlataformaService rolPlataformaService;

    @GetMapping(path = "/id/{id}")
    public ResponseEntity<GenericResponse<Optional<RolPlataforma>>> findById(@PathVariable Long id){
        Optional<RolPlataforma> posibleRolPlataforma = rolPlataformaService.findById(id);
        if(posibleRolPlataforma.isPresent()){
            return new ResponseEntity<>(new GenericResponse<>(posibleRolPlataforma,"rol encontrado con ese id"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new GenericResponse<>(Optional.empty(),"no existe rol con ese id"), HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/nombre/{nombre}")
    public ResponseEntity<GenericResponse<Optional<RolPlataforma>>> findByNombre(@PathVariable String nombre){
        Optional<RolPlataforma> posibleRolPlataforma = rolPlataformaService.findByNombre(nombre);
        if(posibleRolPlataforma.isPresent()){
            return new ResponseEntity<>(new GenericResponse<>(posibleRolPlataforma,"rol encontrado con ese nombre"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new GenericResponse<>(Optional.empty(),"no existe rol con ese nombre"), HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/")
    public ResponseEntity<GenericResponse<List<RolPlataforma>>> findAll(){
        List<RolPlataforma> roles = rolPlataformaService.findAll();
        if(!roles.isEmpty()){
            return new ResponseEntity<>(new GenericResponse<>(roles,"roles encontrados"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new GenericResponse<>(Collections.emptyList(),"sin roles"),HttpStatus.NO_CONTENT);
    }
}
