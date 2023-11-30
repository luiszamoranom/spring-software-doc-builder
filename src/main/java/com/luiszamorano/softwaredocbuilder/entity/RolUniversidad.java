package com.luiszamorano.softwaredocbuilder.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "roluniversidad")
@NoArgsConstructor
public class RolUniversidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    public RolUniversidad(String nombre){
        this.nombre=nombre;
    }
}
