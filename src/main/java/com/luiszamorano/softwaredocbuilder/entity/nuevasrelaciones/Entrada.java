package com.luiszamorano.softwaredocbuilder.entity.nuevasrelaciones;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
// todo repositorio
// todo servicio
// todo controlador
public class Entrada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // entrada <OTM--MTN> contenidoentrada
    @OneToMany(mappedBy = "entrada", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<ContenidoEntrada> contenidoEntradaList = new ArrayList<>();

    // entrada <OTM--MTO> InstanciaModuloProyectoSeccionEntrada
    @OneToMany(mappedBy = "entrada", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<InstanciaModuloProyectoSeccionEntrada> instanciaModuloProyectoSeccionEntradaList = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
    }
}
