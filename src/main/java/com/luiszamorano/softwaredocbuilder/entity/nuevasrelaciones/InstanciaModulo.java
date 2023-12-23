package com.luiszamorano.softwaredocbuilder.entity.nuevasrelaciones;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.luiszamorano.softwaredocbuilder.entity.Modulo;
import com.luiszamorano.softwaredocbuilder.entity.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
// todo controlador
public class InstanciaModulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // modulo <OTM--MTO> instancia
    @ManyToOne
    @JsonManagedReference
    private Modulo modulo;

    @Column
    private int ano;

    @Column
    private int semestre;

    @Column
    private char seccion;

    public InstanciaModulo(Modulo modulo, int ano, int semestre, char seccion, Usuario profeo
    ) {
        this.modulo = modulo;
        this.ano = ano;
        this.semestre = semestre;
        this.seccion = seccion;
        this.profesor = profesor;
    }

    // instanciamodulo <OTM - MTO> instanciamoduloProyecto
    @OneToMany(mappedBy = "instanciaModulo", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<InstanciaModuloProyecto> instanciaModuloProyectoList=new ArrayList<>();

    // instanciaModulo <otm -- mto> UsuarioInstanciaModulo
    @OneToMany(mappedBy = "instanciaModulo", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<UsuarioInstanciaModulo> usuarioInstanciaModuloList=new ArrayList<>() ;

    // usuario <otm -- mto> instanciamodulo
    @ManyToOne
    @JsonManagedReference
    private Usuario profesor;
}
