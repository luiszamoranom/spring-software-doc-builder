package com.luiszamorano.softwaredocbuilder.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
