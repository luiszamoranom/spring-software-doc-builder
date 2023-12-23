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
// todo repositorio
// todo servicio
// todo controlador
public class Seccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    String nombre;

    // seccion <OTM - MTO> InstanciaModuloProyectoSeccion
    @OneToMany(mappedBy = "seccion", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<InstanciaModuloProyectoSeccion> instanciaModuloProyectoSeccionList = new ArrayList<>();
}
