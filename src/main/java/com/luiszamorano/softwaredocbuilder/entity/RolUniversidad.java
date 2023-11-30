package com.luiszamorano.softwaredocbuilder.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "usuarioRolUniversidadUniversidadPk.rolUniversidad")
    @JsonBackReference
    private List<Usuario_RolUniversidad_Universidad> lista_usuario_roluniversidad_universidad = new ArrayList<>();

    public RolUniversidad(String nombre){
        this.nombre=nombre;
    }
}
