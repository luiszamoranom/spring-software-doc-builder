package com.luiszamorano.softwaredocbuilder.entity.pkCompuestas;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.luiszamorano.softwaredocbuilder.entity.InstanciaModulo;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class ProyectoPK implements Serializable {
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "modulo_nombre", referencedColumnName = "modulo_nombre"),
            @JoinColumn(name = "ano", referencedColumnName = "ano"),
            @JoinColumn(name = "semestre", referencedColumnName = "semestre"),
            @JoinColumn(name = "seccion", referencedColumnName = "seccion")
    })
    @JsonBackReference
    private InstanciaModulo instanciaModulo;

    private String nombre;

    public ProyectoPK(String nombre, InstanciaModulo instanciaModulo){
        this.nombre=nombre;
        this.instanciaModulo=instanciaModulo;
    }
}
