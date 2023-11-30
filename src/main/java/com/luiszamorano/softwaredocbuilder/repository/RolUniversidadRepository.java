package com.luiszamorano.softwaredocbuilder.repository;

import com.luiszamorano.softwaredocbuilder.entity.RolUniversidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolUniversidadRepository extends JpaRepository<RolUniversidad, Long> {
    Optional<RolUniversidad> findByNombre(String nombre);
}
