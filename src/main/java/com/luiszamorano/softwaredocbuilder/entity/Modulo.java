package com.luiszamorano.softwaredocbuilder.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "modulo")
@NoArgsConstructor
public class Modulo {
    @Id
    private String nombre;

    @Column
    private String descripcion;

    @Column
    private Boolean estado=true;

    public Modulo(String nombre, String descripcion){
        this.nombre=nombre;
        this.descripcion=descripcion;
    }
}
