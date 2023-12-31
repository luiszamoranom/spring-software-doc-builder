package com.luiszamorano.softwaredocbuilder.repository;

import com.luiszamorano.softwaredocbuilder.entity.InstanciaModulo;
import com.luiszamorano.softwaredocbuilder.entity.Modulo;
import com.luiszamorano.softwaredocbuilder.entity.Usuario;
import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.InstanciaModuloPK;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InstanciaModuloRepository extends JpaRepository<InstanciaModulo, InstanciaModuloPK> {
    List<InstanciaModulo> findByInstanciaModuloPKModulo(Modulo modulo);
    List<InstanciaModulo> findByProfesor(Usuario usuario);
}
