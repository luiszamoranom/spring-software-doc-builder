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
// todo excecpciones
public class InstanciaModuloProyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombreProyecto;

    @ManyToOne
    @JsonManagedReference
    private InstanciaModulo instanciaModulo;

    // instanciamoduloproyecto <OTM - MTO> InstanciaModuloProyectoSeccion
    @OneToMany(mappedBy = "instanciaModuloProyecto", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<InstanciaModuloProyectoSeccion> instanciaModuloProyectoSeccionList = new ArrayList<>();

}
