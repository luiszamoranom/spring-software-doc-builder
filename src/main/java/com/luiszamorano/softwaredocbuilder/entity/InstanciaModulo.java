package com.luiszamorano.softwaredocbuilder.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class InstanciaModulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // modulo <OTM--MTO> instancia
    @ManyToOne
    @JsonManagedReference
    private Modulo modulo;

    // instancia <OTM--MTO> instanciamoduloseccion
    @OneToMany(mappedBy = "instanciaModulo", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<InstanciaModuloSeccion> instanciaModuloSeccionList = new ArrayList<>();


}
