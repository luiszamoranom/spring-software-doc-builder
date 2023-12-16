package com.luiszamorano.softwaredocbuilder.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.luiszamorano.softwaredocbuilder.entity.relation.Usuario_RolProyecto_Proyecto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "rolproyecto")
@NoArgsConstructor
public class RolProyecto {
    @Id
    private String nombre;

    @OneToMany(mappedBy = "rolProyecto")
    @JsonBackReference
    private List<Usuario_RolProyecto_Proyecto> lista_usuario_rolproyecto_proyecto = new ArrayList<>();

    public RolProyecto(String nombre){
        this.nombre=nombre;
    }
}
