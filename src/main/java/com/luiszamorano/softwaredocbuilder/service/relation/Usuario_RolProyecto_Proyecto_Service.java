package com.luiszamorano.softwaredocbuilder.service.relation;

import com.luiszamorano.softwaredocbuilder.entity.relation.Usuario_RolProyecto_Proyecto;
import com.luiszamorano.softwaredocbuilder.repository.refactor.Usuario_RolProyecto_Proyecto_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Usuario_RolProyecto_Proyecto_Service {
    @Autowired
    private Usuario_RolProyecto_Proyecto_Repository usuarioRolProyectoProyectoRepository;

    public Optional<Usuario_RolProyecto_Proyecto> findById(Long id){
        return usuarioRolProyectoProyectoRepository.findById(id);
    }

    public List<Usuario_RolProyecto_Proyecto> findAll(){
        return usuarioRolProyectoProyectoRepository.findAll();
    }
}
