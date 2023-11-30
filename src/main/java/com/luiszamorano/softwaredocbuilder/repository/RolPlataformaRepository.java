package com.luiszamorano.softwaredocbuilder.repository;

import com.luiszamorano.softwaredocbuilder.entity.RolPlataforma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolPlataformaRepository extends JpaRepository<RolPlataforma, Long> {
    Optional<RolPlataforma> findByNombre(String nombre);
}
