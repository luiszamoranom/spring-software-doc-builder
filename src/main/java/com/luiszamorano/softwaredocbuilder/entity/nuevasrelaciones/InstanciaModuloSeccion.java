package com.luiszamorano.softwaredocbuilder.entity.nuevasrelaciones;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
