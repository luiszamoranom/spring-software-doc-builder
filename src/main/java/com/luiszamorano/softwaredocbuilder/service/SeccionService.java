package com.luiszamorano.softwaredocbuilder.service;

import com.luiszamorano.softwaredocbuilder.entity.Seccion;
import com.luiszamorano.softwaredocbuilder.repository.SeccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeccionService {
    @Autowired
    private SeccionRepository seccionRepository;

    public List<Seccion> findAll(){
        return seccionRepository.findAll();
    }

    public Optional<Seccion> findById(String nombre){
        return seccionRepository.findById(nombre);
    }

    public Seccion save(Seccion seccion){
        return seccionRepository.save(seccion);
    }

    public void delete(Seccion seccion){
        seccionRepository.delete(seccion);
    }


}
