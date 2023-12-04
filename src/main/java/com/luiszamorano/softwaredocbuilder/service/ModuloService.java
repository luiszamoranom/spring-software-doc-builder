package com.luiszamorano.softwaredocbuilder.service;

import com.luiszamorano.softwaredocbuilder.entity.Modulo;
import com.luiszamorano.softwaredocbuilder.entity.Universidad;
import com.luiszamorano.softwaredocbuilder.repository.ModuloRepository;
import com.luiszamorano.softwaredocbuilder.response.GenericResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Modulo save(Modulo nuevoModulo) {
        return moduloRepository.save(nuevoModulo);
    }

    public List<Map<String, Object>> cantidadModulosEnUniversidad() {
        List<Object[]> results = moduloRepository.cantidadModulosEnUniversidad();
        return results.stream().map(result -> {
            Map<String, Object> map = new HashMap<>();
            map.put("universidad", result[0]);
            map.put("totalCursos", result[1]);
            return map;
        }).collect(Collectors.toList());
    }


}
