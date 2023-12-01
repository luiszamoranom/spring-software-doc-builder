package com.luiszamorano.softwaredocbuilder.repository;

import com.luiszamorano.softwaredocbuilder.entity.Usuario;
import com.luiszamorano.softwaredocbuilder.entity.Usuario_RolUniversidad_Universidad;
import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.Usuario_RolUniversidad_Universidad_PK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Usuario_RolUniversidad_Universidad_Repository extends JpaRepository<Usuario_RolUniversidad_Universidad, Usuario_RolUniversidad_Universidad_PK> {
    /* notar que busca por PK y bueno, luego busca por la entidad Usuario dentro de la FK*/
    List<Usuario_RolUniversidad_Universidad> findByUsuarioRolUniversidadUniversidadPkUsuario(Usuario usuario);
}
