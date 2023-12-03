package com.luiszamorano.softwaredocbuilder.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "nombre", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Modulo> modulos = new ArrayList<>();

    @OneToMany(mappedBy = "usuarioRolUniversidadUniversidadPk.universidad")
    @JsonBackReference
    private List<Usuario_RolUniversidad_Universidad> lista_usuario_roluniversidad_universidad = new ArrayList<>();

    public Universidad(String abreviacion, String nombre){
        this.abreviacion=abreviacion;
        this.nombre=nombre;
    }
}
