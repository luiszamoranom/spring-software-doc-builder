package com.luiszamorano.softwaredocbuilder.service.nuevo;

import com.luiszamorano.softwaredocbuilder.entity.Modulo;
import com.luiszamorano.softwaredocbuilder.entity.Usuario;
import com.luiszamorano.softwaredocbuilder.entity.nuevasrelaciones.InstanciaModulo;
import com.luiszamorano.softwaredocbuilder.exceptions.entity.instanciamodulo.InstanciaModuloNoEncontradaException;
import com.luiszamorano.softwaredocbuilder.exceptions.entity.instanciamodulo.SinInstanciaModuloException;
import com.luiszamorano.softwaredocbuilder.repository.nueva.InstanciaModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstanciaModuloService {
    @Autowired
    private InstanciaModuloRepository instanciaModuloRepository;

    public List<InstanciaModulo> findAll(){
        List<InstanciaModulo> instancias = instanciaModuloRepository.findAll();
        if(instancias.isEmpty()){
            throw new SinInstanciaModuloException("sin instancias");
        }
        return instancias;
    }

    public InstanciaModulo findById(Long id){
        Optional<InstanciaModulo> posibleInstancia = instanciaModuloRepository.findById(id);
        if(posibleInstancia.isEmpty()){
            throw new InstanciaModuloNoEncontradaException("no se encuetra una instancia con ese id");
        }
        return posibleInstancia.get();
    }

    public InstanciaModulo findByModuloAndAnoAndSemestreAndSeccion(
            Modulo modulo,
            int ano,
            int semestre,
            char seccion
    ){
        Optional<InstanciaModulo> posibleInstancia = instanciaModuloRepository.findByModuloAndAnoAndSemestreAndSeccion(
                modulo, ano, semestre, seccion);
        if(posibleInstancia.isEmpty()){
            throw new InstanciaModuloNoEncontradaException("no se encontro esa instancia con ese modulo, ano, semestre y seccion");
        }
        return posibleInstancia.get();
    }
    public List<InstanciaModulo> findByModulo(Modulo modulo){
        List<InstanciaModulo> instanciasDeModulo = instanciaModuloRepository.findByModulo(modulo);
        if(instanciasDeModulo.isEmpty()){
            throw new SinInstanciaModuloException("sin instancias para ese modulo");
        }
        return instanciasDeModulo;
    }
    public List<InstanciaModulo> findByProfesor(Usuario profesor){
        List<InstanciaModulo> instanciasDeProfesor = instanciaModuloRepository.findByProfesor(profesor);
        if(instanciasDeProfesor.isEmpty()){
            throw new SinInstanciaModuloException("sin instancias asignadas a ese profesor");
        }
        return instanciasDeProfesor;
    }
}
