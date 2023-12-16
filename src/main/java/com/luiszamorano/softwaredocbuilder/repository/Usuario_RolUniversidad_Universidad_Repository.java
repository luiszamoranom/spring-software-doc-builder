package com.luiszamorano.softwaredocbuilder.repository;

import com.luiszamorano.softwaredocbuilder.entity.Usuario;
import com.luiszamorano.softwaredocbuilder.entity.relation.Usuario_RolUniversidad_Universidad;
import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.Usuario_RolUniversidad_Universidad_PK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Usuario_RolUniversidad_Universidad_Repository extends JpaRepository<Usuario_RolUniversidad_Universidad, Usuario_RolUniversidad_Universidad_PK> {
    /* notar que busca por PK y bueno, luego busca por la entidad Usuario dentro de la FK*/
    List<Usuario_RolUniversidad_Universidad> findByUsuarioRolUniversidadUniversidadPkUsuario(Usuario usuario);

    @Query("SELECT u.usuarioRolUniversidadUniversidadPk.universidad.abreviacion, COUNT(u) " +
            "FROM Usuario_RolUniversidad_Universidad u GROUP BY u.usuarioRolUniversidadUniversidadPk.universidad.abreviacion")
    List<Object[]> countUsuariosByUniversidad();
}
