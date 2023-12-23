package com.luiszamorano.softwaredocbuilder.entity.nuevasrelaciones;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ContenidoEntrada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // contenido <OTM--MTN> contenidoentrada
    @ManyToOne
    @JsonManagedReference
    private Contenido contenido;

    // Entrada <OTM--MTN> contenidoentrada
    @ManyToOne
    @JsonManagedReference
    private Entrada entrada;
}
