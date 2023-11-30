package com.luiszamorano.softwaredocbuilder.service;

import com.luiszamorano.softwaredocbuilder.entity.Usuario_RolUniversidad_Universidad;
import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.Usuario_RolUniversidad_Universidad_PK;
import com.luiszamorano.softwaredocbuilder.repository.Usuario_RolUniversidad_Universidad_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Usuario_RolUniversidad_Universidad_Service {
    @Autowired
    private Usuario_RolUniversidad_Universidad_Repository usuarioRolUniversidadUniversidadRepository;

    public List<Usuario_RolUniversidad_Universidad> findAll(){
        return usuarioRolUniversidadUniversidadRepository.findAll();
    }

    public Optional<Usuario_RolUniversidad_Universidad> findById(Usuario_RolUniversidad_Universidad_PK id){
        return usuarioRolUniversidadUniversidadRepository.findById(id);
    }
}
