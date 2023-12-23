package com.luiszamorano.softwaredocbuilder.entity.nuevasrelaciones;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    // InstanciaModuloProyectoSeccion <OTM--MTO> InstanciaModuloProyectoSeccionEntrada
    @OneToMany(mappedBy = "instanciaModuloProyectoSeccion", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<InstanciaModuloProyectoSeccionEntrada> instanciaModuloProyectoSeccionEntradaList = new ArrayList<>();

}
