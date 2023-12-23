package com.luiszamorano.softwaredocbuilder.entity.nuevasrelaciones;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
// todo campos faltantes
// done excepcion
public class InstanciaModuloProyectoSeccionEntrada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // InstanciaModuloProyectoSeccion <OTM--MTO> InstanciaModuloProyectoSeccionEntrada
    @ManyToOne
    @JsonManagedReference
    private InstanciaModuloProyectoSeccion instanciaModuloProyectoSeccion;

    // entrada <OTM--MTO> InstanciaModuloProyectoSeccionEntrada
    @ManyToOne
    @JsonManagedReference
    private Entrada entrada;
}
