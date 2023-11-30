package com.luiszamorano.softwaredocbuilder.service;

import com.luiszamorano.softwaredocbuilder.entity.RolUniversidad;
import com.luiszamorano.softwaredocbuilder.repository.RolUniversidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolUniversidadService {
    @Autowired
    private RolUniversidadRepository rolUniversidadRepository;


    public Optional<RolUniversidad> findById(Long id){
        return rolUniversidadRepository.findById(id);
    }

    public Optional<RolUniversidad> findByNombre(String nombre){
        return rolUniversidadRepository.findByNombre(nombre);
    }

    public List<RolUniversidad> findAll(){
        return rolUniversidadRepository.findAll();
    }
}
