package com.luiszamorano.softwaredocbuilder.repository.nueva;

import com.luiszamorano.softwaredocbuilder.entity.Modulo;
import com.luiszamorano.softwaredocbuilder.entity.Usuario;
import com.luiszamorano.softwaredocbuilder.entity.nuevasrelaciones.InstanciaModulo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InstanciaModuloRepository extends JpaRepository<InstanciaModulo,Long> {
    /*
     this.modulo = modulo;
        this.ano = ano;
        this.semestre = semestre;
        this.seccion = seccion;
     */
    Optional<InstanciaModulo> findByModuloAndAnoAndSemestreAndSeccion(
            Modulo modulo,
            int ano,
            int semestre,
            char seccion
    );
    List<InstanciaModulo> findByModulo(Modulo modulo);
    List<InstanciaModulo> findByProfesor(Usuario usuario);

}
