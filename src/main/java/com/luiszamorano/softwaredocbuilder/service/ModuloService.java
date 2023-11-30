package com.luiszamorano.softwaredocbuilder.service;

import com.luiszamorano.softwaredocbuilder.entity.Modulo;
import com.luiszamorano.softwaredocbuilder.entity.Universidad;
import com.luiszamorano.softwaredocbuilder.repository.ModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuloService {
    @Autowired
    private ModuloRepository moduloRepository;

    public Optional<Modulo> findById(String rut){
        return moduloRepository.findById(rut);
    }

    public List<Modulo> findAll(){
        return moduloRepository.findAll();
    }

    public List<Modulo> findByEstadoTrue(){
        return moduloRepository.findByEstadoTrue();
    }

    public List<Modulo> findByEstadoFalse(){
        return  moduloRepository.findByEstadoFalse();
    }

    public Optional<Modulo> cambiarEstado(String nombre, Boolean estado) {
        Optional<Modulo> posibleModulo = moduloRepository.findById(nombre);
        if(posibleModulo.isPresent()) {
            Modulo modulo = posibleModulo.get();
            modulo.setEstado(estado);
            moduloRepository.save(modulo);
            return Optional.of(modulo);
        }
        return Optional.empty();
    }

    public Optional<Modulo> update(String nombre, String descripcion) {
        Optional<Modulo> posibleModulo = moduloRepository.findById(nombre);
        if(posibleModulo.isPresent()) {
            Modulo modulo = posibleModulo.get();
            modulo.setDescripcion(descripcion);
            moduloRepository.save(modulo);
            return Optional.of(modulo);
        }
        return Optional.empty();
    }


}
