package com.luiszamorano.softwaredocbuilder.service.relation;

import com.luiszamorano.softwaredocbuilder.entity.InstanciaModulo;
import com.luiszamorano.softwaredocbuilder.entity.Usuario;
import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.InstanciaModuloPK;
import com.luiszamorano.softwaredocbuilder.entity.relation.UsuarioEstudiante_InstanciaModulo;
import com.luiszamorano.softwaredocbuilder.repository.refactor.UsuarioEstudiante_InstanciaModulo_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioEstudiante_InstanciaModulo_Service {
    @Autowired
    UsuarioEstudiante_InstanciaModulo_Repository usuarioEstudiante_instanciaModulo_repository;

    public List<UsuarioEstudiante_InstanciaModulo> findAll(){
        return usuarioEstudiante_instanciaModulo_repository.findAll();
    }


    public List<UsuarioEstudiante_InstanciaModulo> findByUsuario(Usuario usuario){
        return usuarioEstudiante_instanciaModulo_repository.findByUsuario(usuario);
    }

    public Optional<UsuarioEstudiante_InstanciaModulo> findById(Long id){
        return usuarioEstudiante_instanciaModulo_repository.findById(id);
    }

    public List<UsuarioEstudiante_InstanciaModulo> findByInstanciaModuloPK(InstanciaModuloPK instanciaModuloPK){
        return usuarioEstudiante_instanciaModulo_repository.findByInstanciaModulo_InstanciaModuloPK(instanciaModuloPK);
    }

    public List<UsuarioEstudiante_InstanciaModulo> findByUsuarioAndInstanciaModuloPK(Usuario usuario,InstanciaModuloPK instanciaModuloPK){
        return usuarioEstudiante_instanciaModulo_repository.findByUsuarioAndInstanciaModulo_InstanciaModuloPK(
                usuario,
                instanciaModuloPK
        );
    }


    public UsuarioEstudiante_InstanciaModulo save(Usuario usuario, InstanciaModulo instanciaModulo){
        UsuarioEstudiante_InstanciaModulo nuevoUsuario = new UsuarioEstudiante_InstanciaModulo(
                usuario,instanciaModulo
        );
        return usuarioEstudiante_instanciaModulo_repository.save(nuevoUsuario);
    }
}
