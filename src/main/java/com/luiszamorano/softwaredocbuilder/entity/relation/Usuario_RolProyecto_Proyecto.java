package com.luiszamorano.softwaredocbuilder.entity.relation;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.luiszamorano.softwaredocbuilder.entity.Proyecto;
import com.luiszamorano.softwaredocbuilder.entity.RolProyecto;
import com.luiszamorano.softwaredocbuilder.entity.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario_rolproyecto_proyecto")
public class Usuario_RolProyecto_Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="usuario_rut")
    @JsonManagedReference
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "rolproyecto_nombre")
    @JsonManagedReference
    private RolProyecto rolProyecto;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "modulo_nombre", referencedColumnName = "modulo_nombre"),
            @JoinColumn(name = "ano", referencedColumnName = "ano"),
            @JoinColumn(name = "semestre", referencedColumnName = "semestre"),
            @JoinColumn(name = "seccion", referencedColumnName = "seccion"),
            @JoinColumn(name = "proyecto_nombre", referencedColumnName = "nombre")
    })
    @JsonManagedReference
    private Proyecto proyecto;
}
