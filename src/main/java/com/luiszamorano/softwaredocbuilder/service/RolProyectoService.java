package com.luiszamorano.softwaredocbuilder.service;

import com.luiszamorano.softwaredocbuilder.entity.RolProyecto;
import com.luiszamorano.softwaredocbuilder.repository.RolProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolProyectoService {
    @Autowired
    private RolProyectoRepository rolProyectoRepository;

    public List<RolProyecto> findAll(){
        return rolProyectoRepository.findAll();
    }
}
