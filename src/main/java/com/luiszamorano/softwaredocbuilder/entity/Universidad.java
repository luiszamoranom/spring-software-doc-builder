package com.luiszamorano.softwaredocbuilder.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="universidad")
@NoArgsConstructor
public class Universidad {
    @Id
    private String abreviacion;

    @Column
    private String nombre;

    @Column
    private boolean estado=true;

    public Universidad(String abreviacion, String nombre){
        this.abreviacion=abreviacion;
        this.nombre=nombre;
    }
}
