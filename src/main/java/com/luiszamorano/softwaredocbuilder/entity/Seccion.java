package com.luiszamorano.softwaredocbuilder.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Seccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // instanciamoduloseccion <MTO--OTM> seccion
    @OneToMany(mappedBy = "seccion", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<InstanciaModuloSeccion> instanciaModuloSeccionList = new ArrayList<>();
}
