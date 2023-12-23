package com.luiszamorano.softwaredocbuilder.entity.nuevasrelaciones;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.luiszamorano.softwaredocbuilder.entity.Modulo;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
// todo campos faltantes
public class InstanciaModulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // modulo <OTM--MTO> instancia
    @ManyToOne
    @JsonManagedReference
    private Modulo modulo;


    // instanciamodulo <OTM - MTO> instanciamoduloProyecto
    @OneToMany(mappedBy = "instanciaModulo", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<InstanciaModuloProyecto> instanciaModuloProyectoList=new ArrayList<>();

    // instanciaModulo <otm -- mto> UsuarioInstanciaModulo
    @OneToMany(mappedBy = "instanciaModulo", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<UsuarioInstanciaModulo> usuarioInstanciaModuloList=new ArrayList<>() ;
}
