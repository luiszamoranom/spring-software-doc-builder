package com.luiszamorano.softwaredocbuilder.entity.nuevasrelaciones;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Entrada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // entrada <OTM - MTO> entradainstanciaseccionentrada
    @OneToMany(mappedBy = "entrada", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<InstanciaModuloSeccionEntrada> instanciaModuloSeccionList = new ArrayList<>();

    // entrada <OTM--MTN> contenidoentrada
    @OneToMany(mappedBy = "entrada", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<ContenidoEntrada> contenidoEntradaList = new ArrayList<>();
}
