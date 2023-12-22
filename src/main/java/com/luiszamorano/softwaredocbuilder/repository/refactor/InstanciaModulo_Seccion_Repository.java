package com.luiszamorano.softwaredocbuilder.repository.refactor;

import com.luiszamorano.softwaredocbuilder.entity.InstanciaModulo;
import com.luiszamorano.softwaredocbuilder.entity.Seccion;
import com.luiszamorano.softwaredocbuilder.entity.relation.InstanciaModulo_Seccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InstanciaModulo_Seccion_Repository extends JpaRepository<InstanciaModulo_Seccion,Long> {
    List<InstanciaModulo_Seccion> findByInstanciaModulo(InstanciaModulo instanciaModulo);
    List<InstanciaModulo_Seccion> findBySeccion(Seccion seccion);

    Optional<InstanciaModulo_Seccion> findByInstanciaModuloAndSeccion(InstanciaModulo instanciaModulo, Seccion seccion);
}
