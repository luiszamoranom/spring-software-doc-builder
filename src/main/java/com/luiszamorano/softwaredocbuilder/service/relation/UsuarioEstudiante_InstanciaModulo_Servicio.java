package com.luiszamorano.softwaredocbuilder.service.relation;

import com.luiszamorano.softwaredocbuilder.entity.InstanciaModulo;
import com.luiszamorano.softwaredocbuilder.entity.Usuario;
import com.luiszamorano.softwaredocbuilder.entity.relation.UsuarioEstudiante_InstanciaModulo;
import com.luiszamorano.softwaredocbuilder.repository.refactor.UsuarioEstudiante_InstanciaModulo_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioEstudiante_InstanciaModulo_Servicio {
    @Autowired
    UsuarioEstudiante_InstanciaModulo_Repository usuarioEstudianteInstanciaModuloRepository;

    public List<UsuarioEstudiante_InstanciaModulo> findAll(){
        return usuarioEstudianteInstanciaModuloRepository.findAll();
    }

    public List<UsuarioEstudiante_InstanciaModulo> findByUsuario(Usuario usuario){
        return usuarioEstudianteInstanciaModuloRepository.findByUsuario(usuario);
    }

    public List<UsuarioEstudiante_InstanciaModulo> findByInstanciaModulo(InstanciaModulo instanciaModulo){
        return usuarioEstudianteInstanciaModuloRepository.findByInstanciaModulo(instanciaModulo);
    }

    public List<UsuarioEstudiante_InstanciaModulo> findByUsuarioAndInstanciaModulo(Usuario usuario, InstanciaModulo instanciaModulo){
        return usuarioEstudianteInstanciaModuloRepository.findByUsuarioAndInstanciaModulo(usuario,instanciaModulo);
    }

    public Optional<UsuarioEstudiante_InstanciaModulo> findById(Long id){
        return usuarioEstudianteInstanciaModuloRepository.findById(id);
    }
}
