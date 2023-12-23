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
public class InstanciaModuloSeccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // instancia <OTM--MTO> instanciamoduloseccion
    @ManyToOne
    @JsonManagedReference
    private InstanciaModulo instanciaModulo;

    // instanciamoduloseccion <MTO--OTM>  seccion
    @ManyToOne
    @JsonManagedReference
    private Seccion seccion;

    // instanciaseccion <OTM - MTO> entradainstanciaseccionentrada
    @OneToMany
    @JsonBackReference
    private List<InstanciaModuloSeccion> instanciaModuloSeccionList = new ArrayList<>();

}
