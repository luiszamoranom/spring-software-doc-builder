package com.luiszamorano.softwaredocbuilder.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(mappedBy = "seccion", fetch = FetchType.LAZY)
    @JsonBackReference
    List<InstanciaModulo_Seccion> instanciaModuloSeccionList;

    public Seccion(String nombre) {
        this.nombre = nombre;
    }
}
