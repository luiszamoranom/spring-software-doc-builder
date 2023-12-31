package com.luiszamorano.softwaredocbuilder.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name="universidad_abreviacion")
    @JsonManagedReference
    private Universidad universidad;

    @OneToMany(mappedBy = "instanciaModuloPK.modulo", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<InstanciaModulo> instancias = new ArrayList<>();

    public Modulo(String nombre, String descripcion, Universidad universidad){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.universidad=universidad;
    }
}
