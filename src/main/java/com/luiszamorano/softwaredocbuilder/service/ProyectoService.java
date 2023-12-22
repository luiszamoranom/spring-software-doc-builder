package com.luiszamorano.softwaredocbuilder.service;

import com.luiszamorano.softwaredocbuilder.entity.Proyecto;
import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.ProyectoPK;
import com.luiszamorano.softwaredocbuilder.repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProyectoService {
    @Autowired
    private ProyectoRepository proyectoRepository;

    public Optional<Proyecto> findById(ProyectoPK id){
        return proyectoRepository.findById(id);
    }

    public List<Proyecto> findAll(){
        return proyectoRepository.findAll();
    }

    public void save(Proyecto proyecto) {
        proyectoRepository.save(proyecto);
    }
}
