package com.luiszamorano.softwaredocbuilder.repository;

import com.luiszamorano.softwaredocbuilder.entity.InstanciaModulo;
import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.InstanciaModuloPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstanciaModuloRepository extends JpaRepository<InstanciaModulo, InstanciaModuloPK> {
}
