package com.luiszamorano.softwaredocbuilder.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.luiszamorano.softwaredocbuilder.entity.pkCompuestas.InstanciaModuloPK;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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

    public InstanciaModulo(InstanciaModuloPK instanciaModuloPK){
        this.instanciaModuloPK=instanciaModuloPK;
    }

}
