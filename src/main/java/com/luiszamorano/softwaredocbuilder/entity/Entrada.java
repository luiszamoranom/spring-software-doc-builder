package com.luiszamorano.softwaredocbuilder.entity;

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
}

