package com.luiszamorano.softwaredocbuilder.entity.pkCompuestas;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.luiszamorano.softwaredocbuilder.entity.Modulo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstanciaModuloPK implements Serializable {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "modulo_nombre")
    @JsonBackReference
    private Modulo modulo;

    @Column
    private int ano;

    @Column
    private int semestre;

    @Column
    private char seccion;
}
