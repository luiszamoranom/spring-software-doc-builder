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
public class Contenido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String texto;

    // contenido <OTM--MTN> contenidoentrada
    @OneToMany(mappedBy = "contenido", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<ContenidoEntrada> contenidoEntradaList = new ArrayList<>();
}
