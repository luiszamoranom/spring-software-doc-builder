package com.luiszamorano.softwaredocbuilder.service.relation;

import com.luiszamorano.softwaredocbuilder.entity.InstanciaModulo;
import com.luiszamorano.softwaredocbuilder.entity.Seccion;
import com.luiszamorano.softwaredocbuilder.entity.relation.InstanciaModulo_Seccion;
import com.luiszamorano.softwaredocbuilder.repository.SeccionRepository;
import com.luiszamorano.softwaredocbuilder.repository.refactor.InstanciaModulo_Seccion_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstanciaModulo_Seccion_Service {
    @Autowired
    private InstanciaModulo_Seccion_Repository instanciaModulo_seccion_repository;

    public List<InstanciaModulo_Seccion> findAll(){
        return instanciaModulo_seccion_repository.findAll();
    }

    public List<InstanciaModulo_Seccion> findByInstanciaModulo(InstanciaModulo instanciaModulo){
        return instanciaModulo_seccion_repository.findByInstanciaModulo(instanciaModulo);
    }

    public Optional<InstanciaModulo_Seccion> findByInstanciaModuloAndSeccion(InstanciaModulo instanciaModulo, Seccion seccion){
        return instanciaModulo_seccion_repository.findByInstanciaModuloAndSeccion(instanciaModulo,seccion);
    }

    public List<InstanciaModulo_Seccion> findBySeccion(Seccion seccion){
        return instanciaModulo_seccion_repository.findBySeccion(seccion);
    }

    public Optional<InstanciaModulo_Seccion> findById(Long id){
        return instanciaModulo_seccion_repository.findById(id);
    }

    public InstanciaModulo_Seccion save(InstanciaModulo_Seccion instanciaModuloSeccion){
        return instanciaModulo_seccion_repository.save(instanciaModuloSeccion);
    }

    public void delete(InstanciaModulo_Seccion instanciaModuloSeccion){
        instanciaModulo_seccion_repository.delete(instanciaModuloSeccion);
    }
}
