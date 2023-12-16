package com.luiszamorano.softwaredocbuilder.repository;

import com.luiszamorano.softwaredocbuilder.entity.InstanciaModulo;
import com.luiszamorano.softwaredocbuilder.entity.Usuario;
import com.luiszamorano.softwaredocbuilder.entity.relation.UsuarioEstudiante_InstanciaModulo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsuarioEstudiante_InstanciaModulo_Repository extends JpaRepository<UsuarioEstudiante_InstanciaModulo, Long> {
    List<UsuarioEstudiante_InstanciaModulo_Repository> findByUsuarioAndInstanciaModulo(Usuario usuario, InstanciaModulo instanciaModulo);
    List<UsuarioEstudiante_InstanciaModulo_Repository> findByUsuario(Usuario usuario);
    List<UsuarioEstudiante_InstanciaModulo_Repository> findByInstancia(InstanciaModulo instanciaModulo);
}
