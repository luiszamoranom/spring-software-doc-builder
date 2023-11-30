package com.luiszamorano.softwaredocbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "rolplataforma")
@NoArgsConstructor
public class RolPlataforma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    public RolPlataforma(String nombre){
        this.nombre=nombre;
    }
}
