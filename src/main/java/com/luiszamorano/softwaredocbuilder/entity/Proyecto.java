package com.luiszamorano.softwaredocbuilder.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.ProyectoPK;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "proyecto")
@NoArgsConstructor
public class Proyecto {
    @EmbeddedId
    private ProyectoPK proyectoPK;

    @OneToMany(mappedBy = "proyecto")
    @JsonBackReference
    private List<Usuario_RolProyecto_Proyecto> lista_usuario_rolproyecto_proyecto = new ArrayList<>();

    public Proyecto(ProyectoPK proyectoPK){
        this.proyectoPK=proyectoPK;
    }
}
