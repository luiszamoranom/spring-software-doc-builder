package com.luiszamorano.softwaredocbuilder.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.InstanciaModuloPK;
import com.luiszamorano.softwaredocbuilder.entity.relation.InstanciaModulo_Seccion;
import com.luiszamorano.softwaredocbuilder.entity.relation.UsuarioEstudiante_InstanciaModulo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "instanciamodulo")
@NoArgsConstructor
public class InstanciaModulo {
    @EmbeddedId
    private InstanciaModuloPK instanciaModuloPK;

    @OneToMany(mappedBy = "proyectoPK.instanciaModulo",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Proyecto> proyectos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "profesor")
    @JsonManagedReference
    private Usuario profesor;

    @OneToMany(mappedBy = "instanciaModulo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UsuarioEstudiante_InstanciaModulo> usuarioEstudianteInstanciaModuloList = new ArrayList<UsuarioEstudiante_InstanciaModulo>();

    @OneToMany(mappedBy = "instanciaModulo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    List<InstanciaModulo_Seccion> instanciaModuloSeccionList;

    public InstanciaModulo(InstanciaModuloPK instanciaModuloPK, Usuario profesor){
        this.instanciaModuloPK=instanciaModuloPK;
        this.profesor=profesor;
    }
}
