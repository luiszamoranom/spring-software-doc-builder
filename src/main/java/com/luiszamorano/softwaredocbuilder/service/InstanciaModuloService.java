package com.luiszamorano.softwaredocbuilder.service;

import com.luiszamorano.softwaredocbuilder.entity.InstanciaModulo;
import com.luiszamorano.softwaredocbuilder.entity.Modulo;
import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.InstanciaModuloPK;
import com.luiszamorano.softwaredocbuilder.repository.InstanciaModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstanciaModuloService {
    @Autowired
    private InstanciaModuloRepository instanciaModuloRepository;

    public Optional<InstanciaModulo> findById(InstanciaModuloPK id){
        return instanciaModuloRepository.findById(id);
    }

    public List<InstanciaModulo> findAll(){
        return instanciaModuloRepository.findAll();
    }

    public List<InstanciaModulo> findByModulo(Modulo modulo){
        return instanciaModuloRepository.findByInstanciaModuloPKModulo(modulo);
    }
}
