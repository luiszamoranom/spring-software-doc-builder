package com.luiszamorano.softwaredocbuilder.service;

import com.luiszamorano.softwaredocbuilder.entity.Universidad;
import com.luiszamorano.softwaredocbuilder.entity.Usuario;
import com.luiszamorano.softwaredocbuilder.repository.UniversidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UniversidadService {
    @Autowired
    private UniversidadRepository universidadRepository;

    public Optional<Universidad> findById(String abreviacion){
        return universidadRepository.findById(abreviacion);
    }

    public List<Universidad> findAll(){
        return universidadRepository.findAll();
    }

    public List<Universidad> findByEstadoTrue(){
        return universidadRepository.findByEstadoTrue();
    }

    public List<Universidad> findByEstadoFalse(){
        return  universidadRepository.findByEstadoFalse();
    }

    public Optional<Universidad> cambiarEstado(String abreviacion, Boolean estado) {
        Optional<Universidad> posibleUniversidad = universidadRepository.findById(abreviacion);
        if(posibleUniversidad.isPresent()) {
            Universidad universidad = posibleUniversidad.get();
            universidad.setEstado(estado);
            universidadRepository.save(universidad);
            return Optional.of(universidad);
        }
        return Optional.empty();
    }

    public Optional<Universidad> update(String abreviacion, String nombre) {
        Optional<Universidad> posibleUniversidad = universidadRepository.findById(abreviacion);
        if(posibleUniversidad.isPresent()) {
            Universidad universidad = posibleUniversidad.get();
            universidad.setNombre(nombre);
            universidadRepository.save(universidad);
            return Optional.of(universidad);
        }
        return Optional.empty();
    }

}
