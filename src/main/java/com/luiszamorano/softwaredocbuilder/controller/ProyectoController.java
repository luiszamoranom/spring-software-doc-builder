package com.luiszamorano.softwaredocbuilder.controller;

import com.luiszamorano.softwaredocbuilder.entity.InstanciaModulo;
import com.luiszamorano.softwaredocbuilder.entity.Modulo;
import com.luiszamorano.softwaredocbuilder.entity.Proyecto;
import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.InstanciaModuloPK;
import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.ProyectoPK;
import com.luiszamorano.softwaredocbuilder.response.GenericResponse;
import com.luiszamorano.softwaredocbuilder.service.InstanciaModuloService;
import com.luiszamorano.softwaredocbuilder.service.ModuloService;
import com.luiszamorano.softwaredocbuilder.service.ProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/proyecto")
public class ProyectoController {
    @Autowired
    private ProyectoService proyectoService;

    @Autowired
    private InstanciaModuloService instanciaModuloService;

    @Autowired
    private ModuloService moduloService;

    private record FindRecord(String nombre, String modulo_nombre, Integer ano, Integer semestre, Character seccion){}
    @GetMapping(path = "/find")
    public ResponseEntity<GenericResponse<Optional<Proyecto>>> findById(@RequestBody FindRecord record){
        Optional<Modulo> posibleModulo=moduloService.findById(record.modulo_nombre);
        if(posibleModulo.isPresent()){
            Optional<InstanciaModulo> posibleInstancia = instanciaModuloService.findById(
                new InstanciaModuloPK(
                        posibleModulo.get(),
                        record.ano,
                        record.semestre,
                        record.seccion
                )
            );

            if(posibleModulo.isPresent()){
                Optional<Proyecto> posibleProyecto = proyectoService.findById(
                        new ProyectoPK(
                                record.nombre,
                                posibleInstancia.get()
                        )
                );

                if(posibleProyecto.isPresent()){
                    return new ResponseEntity<>(new GenericResponse<>(
                            posibleProyecto,
                            "proyecto encontrado"
                    ),HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/")
    public ResponseEntity<GenericResponse<List<Proyecto>>> findAll(){
        List<Proyecto> proyectos = proyectoService.findAll();
        if(!proyectos.isEmpty()){
            new ResponseEntity<>(
                    new GenericResponse<>(
                            proyectos,
                            "proyectos encontrados"
                    ),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
