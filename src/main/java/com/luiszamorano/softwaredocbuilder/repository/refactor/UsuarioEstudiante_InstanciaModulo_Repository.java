package com.luiszamorano.softwaredocbuilder.repository.refactor;

import com.luiszamorano.softwaredocbuilder.entity.InstanciaModulo;
import com.luiszamorano.softwaredocbuilder.entity.Usuario;
import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.InstanciaModuloPK;
import com.luiszamorano.softwaredocbuilder.entity.relation.UsuarioEstudiante_InstanciaModulo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsuarioEstudiante_InstanciaModulo_Repository extends JpaRepository<UsuarioEstudiante_InstanciaModulo, Long> {


    List<UsuarioEstudiante_InstanciaModulo> findByUsuario(Usuario usuario);

    List<UsuarioEstudiante_InstanciaModulo> findByInstanciaModulo_InstanciaModuloPK(InstanciaModuloPK instanciaModuloPK);
    List<UsuarioEstudiante_InstanciaModulo> findByUsuarioAndInstanciaModulo_InstanciaModuloPK(Usuario usuario,InstanciaModuloPK instanciaModuloPK);
    /*
     List<UsuarioEstudiante_InstanciaModulo> findByUsuarioAndInstaciaModulo(Usuario usuario, InstanciaModulo instanciaModulo);
    List<UsuarioEstudiante_InstanciaModulo> findByInstaciaModulo(InstanciaModulo instanciaModulo);
    */

}
