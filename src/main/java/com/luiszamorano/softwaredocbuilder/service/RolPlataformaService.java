package com.luiszamorano.softwaredocbuilder.service;

import com.luiszamorano.softwaredocbuilder.entity.Modulo;
import com.luiszamorano.softwaredocbuilder.entity.RolPlataforma;
import com.luiszamorano.softwaredocbuilder.repository.ModuloRepository;
import com.luiszamorano.softwaredocbuilder.repository.RolPlataformaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolPlataformaService {
    @Autowired
    private RolPlataformaRepository rolPlataformaRepository;


    public Optional<RolPlataforma> findById(Long id){
        return rolPlataformaRepository.findById(id);
    }

    public Optional<RolPlataforma> findByNombre(String nombre){
        return rolPlataformaRepository.findByNombre(nombre);
    }

    public List<RolPlataforma> findAll(){
        return rolPlataformaRepository.findAll();
    }
}
