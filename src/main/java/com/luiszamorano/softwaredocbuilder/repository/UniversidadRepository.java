package com.luiszamorano.softwaredocbuilder.repository;


import com.luiszamorano.softwaredocbuilder.entity.Universidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UniversidadRepository extends JpaRepository<Universidad,String> {
    List<Universidad> findByEstadoTrue();
    List<Universidad> findByEstadoFalse();
}
