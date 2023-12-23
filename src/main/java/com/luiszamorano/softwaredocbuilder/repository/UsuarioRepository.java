package com.luiszamorano.softwaredocbuilder.repository;

import com.luiszamorano.softwaredocbuilder.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,String> {
    List<Usuario> findByEstadoTrue();
    List<Usuario> findByEstadoFalse();

    boolean existsById(String rut);

}
