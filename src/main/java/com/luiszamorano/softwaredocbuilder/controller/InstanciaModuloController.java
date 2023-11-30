package com.luiszamorano.softwaredocbuilder.controller;

import com.luiszamorano.softwaredocbuilder.entity.InstanciaModulo;
import com.luiszamorano.softwaredocbuilder.entity.Modulo;
import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.InstanciaModuloPK;
import com.luiszamorano.softwaredocbuilder.response.GenericResponse;
import com.luiszamorano.softwaredocbuilder.service.InstanciaModuloService;
import com.luiszamorano.softwaredocbuilder.service.ModuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/instanciamodulo")
public class InstanciaModuloController {
    @Autowired
    private InstanciaModuloService instanciaModuloService;

    @Autowired
    private ModuloService moduloService;

    private record findByIdRecord(String nombre, int ano, int semestre, char seccion){}
    @GetMapping(path = "/find")
    private ResponseEntity<GenericResponse<Optional<InstanciaModulo>>> findById(@RequestBody findByIdRecord record){
        Optional<Modulo> posibleModulo = moduloService.findById(record.nombre);
        if(posibleModulo.isPresent()){
            Optional<InstanciaModulo> posibleInstancia = instanciaModuloService.findById(
                new InstanciaModuloPK(
                    posibleModulo.get(), record.ano, record.semestre, record.seccion
                )
            );
            if(posibleInstancia.isPresent()){
                return new ResponseEntity<>(new GenericResponse<>(
                        posibleInstancia,"instancia encontrada"
                ),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/")
    private ResponseEntity<GenericResponse<List<InstanciaModulo>>> findAll(){
        List<InstanciaModulo> instancias = instanciaModuloService.findAll();
        if(!instancias.isEmpty()){
            return new ResponseEntity<>(new GenericResponse<>(
                    instancias,"instancias encontradas"
            ),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
