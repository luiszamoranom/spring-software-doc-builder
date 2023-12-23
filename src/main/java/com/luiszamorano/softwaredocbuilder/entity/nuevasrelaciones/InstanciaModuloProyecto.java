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
// todo campos faltantes
// done excecpciones
public class InstanciaModuloProyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombreProyecto;

    @ManyToOne
    @JsonManagedReference
    private InstanciaModulo instanciaModulo;

    public InstanciaModuloProyecto(String nombreProyecto, InstanciaModulo instanciaModulo) {
        this.nombreProyecto = nombreProyecto;
        this.instanciaModulo = instanciaModulo;
    }

    // instanciamoduloproyecto <OTM - MTO> InstanciaModuloProyectoSeccion
    @OneToMany(mappedBy = "instanciaModuloProyecto", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<InstanciaModuloProyectoSeccion> instanciaModuloProyectoSeccionList = new ArrayList<>();

    // instanciamoduloproyecto <OTM--MTO> UsuarioInstanciaModuloProyecto
    @OneToMany(mappedBy = "instanciaModuloProyecto", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<UsuarioInstanciaModuloProyecto> usuarioInstanciaModuloProyectoList = new ArrayList<>();

}
