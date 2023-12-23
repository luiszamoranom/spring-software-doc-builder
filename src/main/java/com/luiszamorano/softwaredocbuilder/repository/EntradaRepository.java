package com.luiszamorano.softwaredocbuilder.repository;

import com.luiszamorano.softwaredocbuilder.entity.Entrada;
import com.luiszamorano.softwaredocbuilder.entity.Seccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntradaRepository extends JpaRepository<Entrada,Long> {
    List<Entrada> findBySeccion(Seccion seccion);
    Boolean existsById(Seccion seccion);
}
