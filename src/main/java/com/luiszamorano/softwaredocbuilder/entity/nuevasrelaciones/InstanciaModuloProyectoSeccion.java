package com.luiszamorano.softwaredocbuilder.entity.nuevasrelaciones;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
// TODO excepciones
public class InstanciaModuloProyectoSeccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // instanciamoduloproyecto <OTM - MTO> InstanciaModuloProyectoSeccion
    @ManyToOne
    @JsonManagedReference
    private InstanciaModuloProyecto instanciaModuloProyecto;

    // seccion <OTM - MTO> InstanciaModuloProyectoSeccion
    @ManyToOne
    @JsonManagedReference
    private Seccion seccion;

}
