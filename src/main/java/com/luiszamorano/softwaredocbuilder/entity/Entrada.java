package com.luiszamorano.softwaredocbuilder.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Entrada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String contenido;

    @ManyToOne
    @JsonManagedReference
    private Seccion seccion;
}

