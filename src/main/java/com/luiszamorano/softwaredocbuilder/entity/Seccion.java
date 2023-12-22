package com.luiszamorano.softwaredocbuilder.entity;

import com.luiszamorano.softwaredocbuilder.entity.relation.InstanciaModulo_Seccion;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Seccion {
    @Id
    private String nombre;

    @Column
    private int orden;

    @OneToMany(mappedBy = "seccion", fetch = FetchType.LAZY)
    List<InstanciaModulo_Seccion> instanciaModuloSeccionList;

    public Seccion(String nombre) {
        this.nombre = nombre;
    }
}