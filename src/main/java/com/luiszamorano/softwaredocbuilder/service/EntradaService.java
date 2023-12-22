package com.luiszamorano.softwaredocbuilder.service;

import com.luiszamorano.softwaredocbuilder.entity.Entrada;
import com.luiszamorano.softwaredocbuilder.entity.Seccion;
import com.luiszamorano.softwaredocbuilder.repository.EntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntradaService {
    @Autowired
    private EntradaRepository entradaRepository;

    public List<Entrada> findAll(){
        return entradaRepository.findAll();
    }

    public Optional<Entrada> findById(Long id){
        return  entradaRepository.findById(id);
    }

    public List<Entrada> findBySeccion(Seccion seccion){
        return entradaRepository.findBySeccion(seccion);
    }

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


}
