package com.luiszamorano.softwaredocbuilder.controller;

import com.luiszamorano.softwaredocbuilder.entity.Entrada;
import com.luiszamorano.softwaredocbuilder.response.GenericResponse;
import com.luiszamorano.softwaredocbuilder.service.EntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/entrada")
public class EntradaController {

    @Autowired
    private EntradaService entradaService;

    @GetMapping("/")
    public ResponseEntity<GenericResponse<List<Entrada>>> findAll(){
        List<Entrada> entradas = entradaService.findAll();
        if(entradas.size()>0){
            return new ResponseEntity<>(
                    new GenericResponse<>(
                            entradas,"Entradas encontradas"
                    ), HttpStatus.OK
            );
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findById")
    public ResponseEntity<GenericResponse<Entrada>> findById(@RequestParam Long id){
        Optional<Entrada> posibleEntrada = entradaService.findById(id);
        if(posibleEntrada.isPresent()){
            return new ResponseEntity<>(
                    new GenericResponse<>(
                            posibleEntrada.get(),"entrada encontrada con dicho id"
                    ), HttpStatus.OK
            );
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity save(Entrada nuevaEntrada){
        Optional<Entrada> posibleEntrada = entradaService.findById(nuevaEntrada.getId());
        if(posibleEntrada.isEmpty()){
            entradaService.save(nuevaEntrada);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.CONFLICT);
    }

    public ResponseEntity delete(Entrada entradaAEliminar){
        Optional<Entrada> posibleEntrada = entradaService.findById(entradaAEliminar.getId());
        if(posibleEntrada.isPresent()){
            entradaService.delete(entradaAEliminar);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }




    /*


    public boolean save(Entrada nuevaEntrada){
        Optional<Entrada> posibleEntrada = entradaRepository.findById(nuevaEntrada.getId());
        if(posibleEntrada.isPresent()){
            return false;
        }
        entradaRepository.save(nuevaEntrada);
        return true;
    }

    public boolean delete(Entrada entradaAEliminar){
        Optional<Entrada> posibleEntrada = entradaRepository.findById(entradaAEliminar.getId());
        if(posibleEntrada.isPresent()){
            entradaRepository.delete(entradaAEliminar);
            return true;
        }
        return false;
    }
     */
}
