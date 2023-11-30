package com.luiszamorano.softwaredocbuilder.repository;

import com.luiszamorano.softwaredocbuilder.entity.Proyecto;
import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.ProyectoPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProyectoRepository extends JpaRepository<Proyecto, ProyectoPK> {
}
