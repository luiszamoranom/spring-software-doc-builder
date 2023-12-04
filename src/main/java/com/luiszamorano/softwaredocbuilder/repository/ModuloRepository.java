package com.luiszamorano.softwaredocbuilder.repository;

import com.luiszamorano.softwaredocbuilder.entity.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModuloRepository extends JpaRepository<Modulo,String> {
    List<Modulo> findByEstadoTrue();
    List<Modulo> findByEstadoFalse();

    @Query("SELECT m.universidad, COUNT(m) FROM Modulo m GROUP BY m.universidad")
    List<Object[]> cantidadModulosEnUniversidad();
}
