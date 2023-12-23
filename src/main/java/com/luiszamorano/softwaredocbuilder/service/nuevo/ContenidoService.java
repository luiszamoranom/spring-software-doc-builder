package com.luiszamorano.softwaredocbuilder.service.nuevo;

import com.luiszamorano.softwaredocbuilder.entity.nuevasrelaciones.Contenido;
import com.luiszamorano.softwaredocbuilder.exceptions.entity.contenido.ContenidoNoEncontradoException;
import com.luiszamorano.softwaredocbuilder.exceptions.entity.contenido.SinContenidoException;
import com.luiszamorano.softwaredocbuilder.repository.nueva.ContenidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContenidoService {
    @Autowired
    private ContenidoRepository contenidoRepository;

    public Contenido findById(Long id){
        Optional<Contenido> posibleContenido = contenidoRepository.findById(id);
        if(posibleContenido.isEmpty()){
            throw new ContenidoNoEncontradoException("no existe contenido con ese id");
        }
        return posibleContenido.get();
    }

    public List<Contenido> findAll() {
        List<Contenido> contenidos = contenidoRepository.findAll();
        if (contenidos.isEmpty()) {
            throw new SinContenidoException("sin contenido");
        }
        return contenidos;
    }

}
