package com.luiszamorano.softwaredocbuilder.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="usuario")
@NoArgsConstructor
public class Usuario {
    @Id
    private String rut;

    @Column
    private String nombres;

    @Column
    private String apellidos;

    @Column
    private String contrasena;

    @Column
    private String email;

    @Column
    private boolean estado = true;

    @Column
    private String rol_plataforma="Usuario";

    public Usuario(String rut, String nombres, String apellidos, String contrasena, String email){
        this.rut=rut;
        this.nombres=nombres;
        this.apellidos=apellidos;
        this.contrasena=contrasena;
        this.email=email;
    }

    @OneToMany(mappedBy = "usuario")
    @JsonBackReference
    List<Usuario_RolProyecto_Proyecto> lista_usuario_rolproyecto_proyecto  = new ArrayList<>();
}
