package com.luiszamorano.softwaredocbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class InstanciaModulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Modulo modulo;
}
