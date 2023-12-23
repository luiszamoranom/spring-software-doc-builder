package com.luiszamorano.softwaredocbuilder.entity.nuevasrelaciones;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class InstanciaModuloSeccionEntrada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // instanciaseccion <OTM - MTO> entradainstanciaseccionentrada
    @ManyToOne
    @JsonManagedReference
    private InstanciaModuloSeccion instanciaModuloSeccion;

    // entrada <OTM - MTO> entradainstanciaseccionentrada
    @ManyToOne
    @JsonManagedReference
    private Entrada entrada;
}
